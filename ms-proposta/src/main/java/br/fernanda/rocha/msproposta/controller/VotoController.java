package br.fernanda.rocha.msproposta.controller;

import br.fernanda.rocha.msproposta.MensagemResposta;
import br.fernanda.rocha.msproposta.Proposta;
import br.fernanda.rocha.msproposta.Voto;
import br.fernanda.rocha.msproposta.cliente.FuncionariosClient;
import br.fernanda.rocha.msproposta.dto.ContagemVotosDTO;
import br.fernanda.rocha.msproposta.dto.DadosVotos;
import br.fernanda.rocha.msproposta.repository.PropostaRepository;
import br.fernanda.rocha.msproposta.repository.VotoRepository;
import br.fernanda.rocha.msproposta.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/votos")
public class VotoController {

    @Autowired
    private VotoService votoService;

    @Autowired
    private FuncionariosClient funcionarioClient;
    @Autowired
    private VotoRepository repository;

    @Autowired
    private PropostaRepository propostaRepository;

    @PostMapping
    public ResponseEntity<MensagemResposta> votar(@RequestBody DadosVotos dadosVotos) {
        try {

            boolean funcionarioCadastrado = verificarFuncionario(dadosVotos.cpfFuncionario());

            if (!funcionarioCadastrado) {
                return ResponseEntity.badRequest().body(new MensagemResposta("Apenas funcionários cadastrados podem votar."));
            }

            Optional<Proposta> optionalProposta = propostaRepository.findById(dadosVotos.propostaId());
            if (optionalProposta.isPresent()) {
                Proposta proposta = optionalProposta.get();
                if (proposta.isVotacaoExpirada()) {
                    return ResponseEntity.badRequest().body(new MensagemResposta("A votação para esta proposta já expirou."));
                }
            }
            
            

            if (votoService.verificaVotoDuplicado(dadosVotos.cpfFuncionario(), dadosVotos.propostaId())) {
                return ResponseEntity.badRequest().body(new MensagemResposta("Este CPF já votou nesta proposta."));
            }


            var voto = new Voto(dadosVotos);
            if (dadosVotos.getVoto() != null) {
                voto.setAprovado("Aprovado".equalsIgnoreCase(dadosVotos.getVoto()));
            } else {

                voto.setAprovado(false);
            }
            repository.save(voto);



            

            return ResponseEntity.ok(new MensagemResposta("Voto registrado com sucesso."));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MensagemResposta("Erro ao processar o voto: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<Voto>> listarVotos() {
        List<Voto> votos = votoService.listarVotos();
        return ResponseEntity.ok(votos);
    }

    @GetMapping("/verificarFuncionario")
    public boolean verificarFuncionario(@RequestParam("cpf") String cpf) {
        return funcionarioClient.verificarFuncionarioCadastrado(cpf);
    }
// teste
    @GetMapping("/contagem-votos-por-proposta")
    public ResponseEntity<Map<Long, ContagemVotosDTO>> contarVotosPorProposta() {
        Map<Long, ContagemVotosDTO> contagemPorProposta = votoService.contarVotosPorProposta();
        return ResponseEntity.ok(contagemPorProposta);
    }


}
