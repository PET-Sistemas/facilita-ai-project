package com.UFMSPetSistemas.getpet.model.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;

    private double valor;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "usuario_prestador_id")
    private Usuario usuarioPrestador;
    
    @ManyToOne
    @JoinColumn(name = "usuario_consumidor_id")
    private Usuario usuarioConsumidor;

    @OneToMany(mappedBy = "servico", cascade = CascadeType.ALL)

    private List<PrestacaoServico> avaliacoes; // Relacionamento "um para muitos"

    public Servico(
                   String titulo,
                   String descricao,
                   double valor,
                   Categoria categoria,
                   Usuario usuarioPrestador,
                   Usuario usuarioConsumidor
    ) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.usuarioPrestador = usuarioPrestador;
        this.usuarioConsumidor = usuarioConsumidor;
    }

    public Servico(){}

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public List<PrestacaoServico> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<PrestacaoServico> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Usuario getUsuario() {
        return usuarioConsumidor;
    }

    public void setUsuario(Usuario usuarioConsumidor) {
        this.usuarioConsumidor = usuarioConsumidor;
    }

    public Usuario getUsuarioPrestador() {
        return usuarioPrestador;
    }

    public void setUsuarioPrestador(Usuario usuarioPrestador) {
        this.usuarioPrestador = usuarioPrestador;
    }
}

