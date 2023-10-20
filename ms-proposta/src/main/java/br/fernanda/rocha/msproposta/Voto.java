package br.fernanda.rocha.msproposta;

import br.fernanda.rocha.msproposta.dto.DadosVotos;
import br.fernanda.rocha.msproposta.status.StatusVoto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private boolean aprovado;
    @Column(name = "REJEITADO", columnDefinition = "BOOLEAN DEFAULT false")
    private boolean rejeitado;



    public boolean isRejeitado() {
        return rejeitado;
    }

    public void setRejeitado(boolean rejeitado) {
        this.rejeitado = rejeitado;
    }

    @ManyToOne
    private Proposta proposta;

    @Enumerated(EnumType.STRING)
    private StatusVoto status;

    public Voto(DadosVotos dadosVotos) {
        this.cpf = dadosVotos.cpfFuncionario();
        this.proposta = new Proposta(dadosVotos.propostaId());

        if (dadosVotos.getVoto() != null) {
            if ("APROVADO".equalsIgnoreCase(dadosVotos.getVoto())) {
                this.aprovado = true;
                this.rejeitado = false;
            } else if ("REJEITADO".equalsIgnoreCase(dadosVotos.getVoto())) {
                this.aprovado = false;
                this.rejeitado = true;
            } else {

                throw new IllegalArgumentException("Valor de voto inválido: " + dadosVotos.getVoto());
            }
        } else {
            this.aprovado = false;
            this.rejeitado = false;
        }
    }



    public StatusVoto getStatus() {
        return status;
    }

    public void setStatus(StatusVoto status) {
        this.status = status;
    }


    public Voto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public void setProposta(Proposta proposta) {
        this.proposta = proposta;
    }

    public String getCpfFuncionario() {
        return null;
    }

    public String getVoto() {
        return null;
    }
}
