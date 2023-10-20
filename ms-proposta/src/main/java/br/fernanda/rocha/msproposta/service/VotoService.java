package br.fernanda.rocha.msproposta.service;
import br.fernanda.rocha.msproposta.Voto;
import br.fernanda.rocha.msproposta.cliente.FuncionariosClient;
import br.fernanda.rocha.msproposta.dto.ContagemVotosDTO;
import br.fernanda.rocha.msproposta.dto.VotoDetalheDTO;
import br.fernanda.rocha.msproposta.repository.VotoRepository;
import br.fernanda.rocha.msproposta.status.StatusVoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VotoService {

    private final VotoRepository votoRepository;
    private final FuncionariosClient funcionarioClient;

    @Autowired
    public VotoService(VotoRepository votoRepository, FuncionariosClient funcionarioClient) {
        this.votoRepository = votoRepository;
        this.funcionarioClient = funcionarioClient;
    }


    public Voto enviarVoto(Voto voto) {
        boolean funcionarioCadastrado = funcionarioClient.verificarFuncionarioCadastrado(voto.getCpfFuncionario());

        if (!funcionarioCadastrado) {

        }
        if (votoRepository.existsByCpfAndPropostaId(voto.getCpfFuncionario(), voto.getProposta().getId())) {

        }


        if (voto.getVoto() != null && "Aprovado".equalsIgnoreCase(voto.getVoto())) {
            voto.setAprovado(true);
        } else {
            voto.setAprovado(false);
        }



        if ("APROVADO".equalsIgnoreCase(voto.getVoto())) {
            voto.setStatus(StatusVoto.APROVADO);
        } else if ("REJEITADO".equalsIgnoreCase(voto.getVoto())) {
            voto.setStatus(StatusVoto.REJEITADO);
        } else {

        }

        Voto votoSalvo = votoRepository.save(voto);
        return votoSalvo;
    }


    public List<Voto> listarVotos() {
        return votoRepository.findAll();
    }



    private boolean contagemVotosExcedido(String cpf, Long propostaId) {
        List<Voto> votos = votoRepository.findByCpfAndPropostaId(cpf, propostaId);
        return !votos.isEmpty();
    }
    public ContagemVotosDTO contarVotos() {
        List<Voto> votos = votoRepository.findAll();
        ContagemVotosDTO contagem = new ContagemVotosDTO();
        Map<String, StatusVoto> votosPorCPF = new HashMap<>();

        for (Voto voto : votos) {
            String cpf = voto.getCpf();
            StatusVoto status = voto.getStatus();

            if (!votosPorCPF.containsKey(cpf) && !contagemVotosExcedido(cpf, voto.getProposta().getId())) {
                votosPorCPF.put(cpf, status);

                contagem.setTotalVotos(contagem.getTotalVotos() + 1);

                if (status == StatusVoto.APROVADO) {
                    contagem.setVotosAprovados(contagem.getVotosAprovados() + 1);
                } else if (status == StatusVoto.REJEITADO) {
                    contagem.setVotosRejeitados(contagem.getVotosRejeitados() + 1);
                }

                VotoDetalheDTO detalhe = new VotoDetalheDTO();
                detalhe.setCpf(cpf);
                detalhe.setStatus(status);

                contagem.getVotosDetalhe().add(detalhe);
            }
        }

        return contagem;
    }

    public boolean verificaVotoDuplicado(String cpf, Long propostaId) {
        return votoRepository.existsByCpfAndPropostaId(cpf, propostaId);
    }

    public Map<Long, ContagemVotosDTO> contarVotosPorProposta() {
        List<Voto> votos = votoRepository.findAll();
        Map<Long, ContagemVotosDTO> contagemPorProposta = new HashMap<>();

        for (Voto voto : votos) {
            Long propostaId = voto.getProposta().getId();
            if (!contagemPorProposta.containsKey(propostaId)) {
                contagemPorProposta.put(propostaId, new ContagemVotosDTO());
            }

            ContagemVotosDTO contagem = contagemPorProposta.get(propostaId);

            contagem.setTotalVotos(contagem.getTotalVotos() + 1);

            if (voto.isAprovado()) {
                contagem.setVotosAprovados(contagem.getVotosAprovados() + 1);
            } else {
                contagem.setVotosRejeitados(contagem.getVotosRejeitados() + 1);
            }
        }

        return contagemPorProposta;
    }



}
