package br.fernanda.rocha.msproposta.service;

import br.fernanda.rocha.msproposta.Proposta;
import br.fernanda.rocha.msproposta.repository.PropostaRepository;
import br.fernanda.rocha.msproposta.status.StatusProposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EncerrarVotacaoService {

    private final PropostaService propostaService;

    private final PropostaRepository propostaRepository;

    @Autowired
    public EncerrarVotacaoService(PropostaService propostaService, PropostaRepository propostaRepository) {
        this.propostaService = propostaService;
        this.propostaRepository = propostaRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void encerrarVotacoesExpiradas() {
        List<Proposta> propostas = propostaRepository.findAll();
        Date now = new Date();
        propostaService.verificarEAtualizarPropostasEncerradas();

        for (Proposta proposta : propostas) {
            if (proposta.isVotacaoExpirada(now) && !proposta.getStatus().equals(StatusProposta.ENCERRADA)) {
                proposta.setStatus(StatusProposta.ENCERRADA);
                propostaRepository.save(proposta);
            }
        }
    }
}
