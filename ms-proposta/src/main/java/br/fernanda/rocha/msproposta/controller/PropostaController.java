package br.fernanda.rocha.msproposta.controller;

import br.fernanda.rocha.msproposta.InfoProposta;
import br.fernanda.rocha.msproposta.MensagemResposta;
import br.fernanda.rocha.msproposta.Proposta;
import br.fernanda.rocha.msproposta.service.PropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    @Autowired
    private PropostaService propostaService;

    @PostMapping
    public ResponseEntity<MensagemResposta> criarProposta(@RequestBody Proposta proposta) {

        Proposta novaProposta = propostaService.criarProposta(proposta);

        if (novaProposta.getId() != null) {
            String mensagem = "Proposta criada com sucesso - ID: " + novaProposta.getId() +
                    ", Descrição: " + novaProposta.getDescricao();
            MensagemResposta resposta = new MensagemResposta(mensagem);
            return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MensagemResposta("Falha ao criar a proposta."));
    }



    @GetMapping("/lista")
    public ResponseEntity<List<Proposta>> listarPropostas() {
        List<Proposta> propostas = propostaService.listarPropostas();
        return ResponseEntity.ok(propostas);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Proposta> obterPropostaPorId(@PathVariable Long id) {
        Proposta proposta = propostaService.obterPropostaPorId(id);
        if (proposta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proposta);
    }

}