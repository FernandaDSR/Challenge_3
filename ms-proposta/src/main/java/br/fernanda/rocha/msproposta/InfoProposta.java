package br.fernanda.rocha.msproposta;

import java.util.List;

public class InfoProposta {
    private Long id;
    private String descricao;
    private long tempoRestanteEmMinutos;
    private int totalPropostas;
    private int totalAprovados;
    private int totalRejeitados;
    private List<Proposta> propostas;

    public int getTotalPropostas() {
        return totalPropostas;
    }

    public void setTotalPropostas(int totalPropostas) {
        this.totalPropostas = totalPropostas;
    }

    public int getTotalAprovados() {
        return totalAprovados;
    }

    public void setTotalAprovados(int totalAprovados) {
        this.totalAprovados = totalAprovados;
    }

    public int getTotalRejeitados() {
        return totalRejeitados;
    }

    public void setTotalRejeitados(int totalRejeitados) {
        this.totalRejeitados = totalRejeitados;
    }

    public List<Proposta> getPropostas() {
        return propostas;
    }

    public void setPropostas(List<Proposta> propostas) {
        this.propostas = propostas;
    }

    public InfoProposta(Long id, String descricao, long tempoRestanteEmMinutos) {
        this.id = id;
        this.descricao = descricao;
        this.tempoRestanteEmMinutos = tempoRestanteEmMinutos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getTempoRestanteEmMinutos() {
        return tempoRestanteEmMinutos;
    }

    public void setTempoRestanteEmMinutos(long tempoRestanteEmMinutos) {
        this.tempoRestanteEmMinutos = tempoRestanteEmMinutos;
    }
}

