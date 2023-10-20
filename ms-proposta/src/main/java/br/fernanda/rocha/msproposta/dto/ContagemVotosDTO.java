package br.fernanda.rocha.msproposta.dto;

import br.fernanda.rocha.msproposta.status.StatusVoto;

import java.util.List;

public class ContagemVotosDTO {
    private int totalVotos;
    private int votosAprovados;
    private int votosRejeitados;
    private List<VotoDetalheDTO> votosDetalhe;

    public int getTotalVotos() {
        return totalVotos;
    }

    public void setTotalVotos(int totalVotos) {
        this.totalVotos = totalVotos;
    }

    public int getVotosAprovados() {
        return votosAprovados;
    }

    public void setVotosAprovados(int votosAprovados) {
        this.votosAprovados = votosAprovados;
    }

    public int getVotosRejeitados() {
        return votosRejeitados;
    }

    public void setVotosRejeitados(int votosRejeitados) {
        this.votosRejeitados = votosRejeitados;
    }

    public List<VotoDetalheDTO> getVotosDetalhe() {
        return votosDetalhe;
    }

    public void setVotosDetalhe(List<VotoDetalheDTO> votosDetalhe) {
        this.votosDetalhe = votosDetalhe;
    }
}

