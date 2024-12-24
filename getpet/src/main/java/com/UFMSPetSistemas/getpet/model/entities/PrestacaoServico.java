package com.UFMSPetSistemas.getpet.model.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Date;
import java.util.Collection;
import java.util.Objects;


@Entity
public class PrestacaoServico {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Temporal(TemporalType.DATE)
    private Date dataPrestacao;


    @Min(value = 1, message = "A avaliação deve ser no mínimo 1.")
    @Max(value = 5, message = "A avaliação deve ser no máximo 5.")
    private int avaliacao;


    @Size(max = 255, message = "A descrição da avaliação deve ter no máximo 255 caracteres.")
    private String avaliacaoDescricao;


    @Positive(message = "O valor do serviço deve ser positivo.")
    private double valorServico;


    @OneToMany
    private Collection<Usuario> usuarioConsumidor;


    @OneToMany
    private Collection<Usuario> usuarioServico;


    @Min(value = 1, message = "A avaliação do serviço deve ser no mínimo 1.")
    @Max(value = 5, message = "A avaliação do serviço deve ser no máximo 5.")
    private int avaliacaoServico;

	@ManyToOne
	@JoinColumn(name = "tipo_servico_id", referencedColumnName = "id")
	private TipoServico tipoServico;


	public TipoServico getTipoServico(){
		return tipoServico;
	}


	public void setTipoServico(TipoServico tipoNovo){
		this.tipoServico = tipoNovo;
	}


    // Getters e Setters
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Date getDataPrestacao() {
        return dataPrestacao;
    }


    public void setDataPrestacao(Date dataPrestacao) {
        this.dataPrestacao = dataPrestacao;
    }


    public int getAvaliacao() {
        return avaliacao;
    }


    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }


    public String getAvaliacaoDescricao() {
        return avaliacaoDescricao;
    }


    public void setAvaliacaoDescricao(String avaliacaoDescricao) {
        this.avaliacaoDescricao = avaliacaoDescricao;
    }


    public double getValorServico() {
        return valorServico;
    }


    public void setValorServico(double valorServico) {
        this.valorServico = valorServico;
    }


    public Collection<Usuario> getUsuarioConsumidor() {
        return usuarioConsumidor;
    }


    public void setUsuarioConsumidor(Collection<Usuario> usuarioConsumidor) {
        this.usuarioConsumidor = usuarioConsumidor;
    }


    public Collection<Usuario> getUsuarioServico() {
        return usuarioServico;
    }


    public void setUsuarioServico(Collection<Usuario> usuarioServico) {
        this.usuarioServico = usuarioServico;
    }


    public int getAvaliacaoServico() {
        return avaliacaoServico;
    }


    public void setAvaliacaoServico(int avaliacaoServico) {
        this.avaliacaoServico = avaliacaoServico;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrestacaoServico that = (PrestacaoServico) o;
        return Objects.equals(id, that.id);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
