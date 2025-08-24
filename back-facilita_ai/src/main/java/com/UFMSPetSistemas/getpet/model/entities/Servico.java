package com.UFMSPetSistemas.getpet.model.entities;

import jakarta.persistence.*;

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

    public Servico(
                   String titulo,
                   String descricao,
                   double valor,
                   Categoria categoria,
                   Usuario usuarioPrestador
    ) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.usuarioPrestador = usuarioPrestador;
    }

    public Servico(){}

    // GETTERS

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Usuario getUsuarioPrestador() {
        return usuarioPrestador;
    }

    // SETTERS

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setUsuarioPrestador(Usuario usuarioPrestador) {
        this.usuarioPrestador = usuarioPrestador;
    }
}

