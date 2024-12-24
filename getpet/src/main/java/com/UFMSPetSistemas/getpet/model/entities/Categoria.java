package com.UFMSPetSistemas.getpet.model.entities;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @OneToMany(mappedBy = "categoria")
    private Collection<TipoServico> tipoServico;

    // Getters and Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Collection<TipoServico> getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(Collection<TipoServico> tipoServico) {
        this.tipoServico = tipoServico;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Categoria categoria = (Categoria) o;
		return codigo == categoria.codigo;
	}

	@Override
	public int hashCode() {
		return Integer.hashCode(codigo);
	}

}
