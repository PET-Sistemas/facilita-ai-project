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
    @JoinColumn(name = "user_prestador_id")
    private Usuario usuario_prestador;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario_consumidor;

    @OneToMany(mappedBy = "servico", cascade = CascadeType.ALL)
    private List<PrestacaoServico> avaliacoes; // Relacionamento "um para muitos"

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return usuario_consumidor;
    }

    public void setUsuario(Usuario usuario_consumidor) {
        this.usuario_consumidor = usuario_consumidor;
    }

    public Usuario getUsuario_prestador() {
        return usuario_prestador;
    }

    public void setUsuario_prestador(Usuario usuario_prestador) {
        this.usuario_prestador = usuario_prestador;
    }
}

