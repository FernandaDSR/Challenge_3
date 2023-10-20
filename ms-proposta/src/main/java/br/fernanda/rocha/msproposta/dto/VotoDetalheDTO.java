package br.fernanda.rocha.msproposta.dto;

import br.fernanda.rocha.msproposta.status.StatusVoto;

public class VotoDetalheDTO {
    private String cpf;
    private StatusVoto status;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public StatusVoto getStatus() {
        return status;
    }

    public void setStatus(StatusVoto status) {
        this.status = status;
    }
}
