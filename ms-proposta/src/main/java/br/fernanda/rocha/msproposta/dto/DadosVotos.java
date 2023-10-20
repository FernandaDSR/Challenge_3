package br.fernanda.rocha.msproposta.dto;

import br.fernanda.rocha.msproposta.status.StatusVoto;

public record DadosVotos(String cpfFuncionario, long propostaId, StatusVoto voto) {

    public String getVoto() {
        if (voto == StatusVoto.APROVADO) {
            return "APROVADO";
        } else if (voto == StatusVoto.REJEITADO) {
            return "REJEITADO";
        } else {
            return null;
        }
    }


    public String getCpfFuncionario() {
        return cpfFuncionario;
    }
}