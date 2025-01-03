package com.UFMSPetSistemas.getpet.model.entities;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tipo_servico")
public class TipoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome_servico", nullable = false, length = 100)
    private String nomeServico;

    @ManyToOne
    @JoinColumn(name = "tipo_servico_pai_id", referencedColumnName = "id")
    private TipoServico tipoServico;

    @ManyToOne
    @JoinColumn(name = "usuario_fornecedor_id", referencedColumnName = "id")
    private Collection<UsuarioFornecedor> usuarioServico;

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "codigo")
    private Categoria categoria;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }

    public Collection<UsuarioFornecedor> getUsuarioServico() {
        return usuarioServico;
    }

    public void setUsuarioServico(Collection<UsuarioFornecedor> usuarioServico) {
        this.usuarioServico = usuarioServico;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TipoServico that = (TipoServico) o;
		return id == that.id;
	}

	@Override
	public int hashCode() {
		return Integer.hashCode(id);
	}

}
