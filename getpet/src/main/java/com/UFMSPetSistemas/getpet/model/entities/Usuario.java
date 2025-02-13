package com.UFMSPetSistemas.getpet.model.entities;

import com.UFMSPetSistemas.getpet.model.entities.Servico;
import com.UFMSPetSistemas.getpet.model.entities.PrestacaoServico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Usuario {
	@Id @GeneratedValue
    private Long id;

    @NotBlank(message = "O nome completo é obrigatório.")
	private String nomeCompleto;

    @Temporal(TemporalType.DATE)
	private Date dataNascimento;

    @NotBlank(message = "O endereço é obrigatório.")
	private String endereco;

    @NotBlank(message = "A cidade é obrigatória.")
	private String cidade;

    @NotBlank(message = "O estado (UF) é obrigatório.")
	private String uf;

    @NotBlank(message = "O e-mail é obrigatório.")
	private String email;

	@NotBlank(message = "O telefone é obrigatório.")
	@Pattern(regexp = "\\d{11}", message = "O telefone deve ter 11 dígitos numéricos.")
	private String telefone;

	private String senha;

    @OneToMany(mappedBy = "user_prestador_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Servico> servicos;

	@OneToMany(mappedBy = "user_prestador_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrestacaoServico> servicosPrestados;

	@OneToMany(mappedBy = "user_contratante_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrestacaoServico> servicosContratados;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Usuario usuario = (Usuario) o;
		return Objects.equals(id, usuario.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}