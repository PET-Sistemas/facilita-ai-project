package com.UFMSPetSistemas.getpet.model.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class PrestacaoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date dataprestacao;

    private int avaliacao;

    private String avaliacaodesc;

    @ManyToOne
    @JoinColumn(name = "usuario_consumidor_id")
    private Usuario usuarioConsumidor;
    
    @ManyToOne
    @JoinColumn(name = "usuario_prestador_id")
    private Usuario usuarioFornecedor;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataprestacao() {
        return dataprestacao;
    }

    public void setDataprestacao(Date dataprestacao) {
        this.dataprestacao = dataprestacao;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getAvaliacaodesc() {
        return avaliacaodesc;
    }

    public void setAvaliacaodesc(String avaliacaodesc) {
        this.avaliacaodesc = avaliacaodesc;
    }

    public Usuario getUsuarioConsumidor() {
        return usuarioConsumidor;
    }

    public void setUsuarioConsumidor(Usuario usuarioConsumidor) {
        this.usuarioConsumidor = usuarioConsumidor;
    }

    public Usuario getUsuarioFornecedor() {
        return usuarioFornecedor;
    }

    public void setUsuarioFornecedor(Usuario usuarioFornecedor) {
        this.usuarioFornecedor = usuarioFornecedor;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}