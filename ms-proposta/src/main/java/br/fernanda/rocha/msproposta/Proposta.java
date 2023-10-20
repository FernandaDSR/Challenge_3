package br.fernanda.rocha.msproposta;

import br.fernanda.rocha.msproposta.status.StatusProposta;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Proposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private int tempoDuracao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @Enumerated(EnumType.STRING)
    private StatusProposta status;


    public Proposta() {
        status = StatusProposta.ABERTA;
    }



    public Proposta(long id) {
        this.id = id;

    }

    public int getTempoDuracao() {
        return tempoDuracao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setTempoDuracao(int tempoDuracao) {
        this.tempoDuracao = tempoDuracao;
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

    public void setDataCriacao(Date date) {
    }

    public boolean isVotacaoExpirada(Date now) {
        return false;
    }

    public Object getStatus() {
        return null;
    }

    public boolean isVotacaoExpirada() {
        if (dataCriacao != null && tempoDuracao > 0) {
            Date now = new Date();
            Date dataExpiracao = new Date(dataCriacao.getTime() + tempoDuracao * 60 * 1000);
            return now.after(dataExpiracao);
        }
        // Se dataCriacao ou tempoDuracao não estiverem definidos, a votação não está expirada
        return false;
    }


    public void setStatus(StatusProposta statusProposta) {
    }
}


