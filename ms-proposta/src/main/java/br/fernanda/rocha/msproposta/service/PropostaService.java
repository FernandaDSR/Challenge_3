package br.fernanda.rocha.msproposta.service;

import br.fernanda.rocha.msproposta.Proposta;
import br.fernanda.rocha.msproposta.repository.PropostaRepository;
import br.fernanda.rocha.msproposta.status.StatusProposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PropostaService {

    @Autowired
    private PropostaRepository propostaRepository;


    public Proposta criarProposta(Proposta proposta) {

        return propostaRepository.save(proposta);
    }


    public List<Proposta> listarPropostas() {

        return propostaRepository.findAll();
    }


    public Proposta obterPropostaPorId(Long id) {
        Optional<Proposta> optionalProposta = propostaRepository.findById(id);
        return optionalProposta.orElse(null);
    }



    public void verificarEAtualizarPropostasEncerradas() {
        List<Proposta> propostas = propostaRepository.findAll();
        Date now = new Date();

        for (Proposta proposta : propostas) {
            if (proposta.isVotacaoExpirada(now) && !proposta.getStatus().equals(StatusProposta.ENCERRADA)) {
                proposta.setStatus(StatusProposta.ENCERRADA);
                propostaRepository.save(proposta);
            }
        }
    }
}

