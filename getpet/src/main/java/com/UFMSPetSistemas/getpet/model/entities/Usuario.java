package com.UFMSPetSistemas.getpet.model.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Past;
import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Usuario {
	private @Id @GeneratedValue
	Long id;

	private @NotBlank(message = "O nome completo é obrigatório.")
	@Size(min = 3, max = 50, message = "O nome completo deve ter entre 3 e 50 caracteres.")
	String nomeCompleto;

	private @Past(message = "A data de nascimento deve ser uma data passada.")
	@Temporal(TemporalType.DATE)
	Date dataNascimento;


	private @NotBlank(message = "O endereço é obrigatório.")
	@Size(max = 100, message = "O endereço deve ter no máximo 100 caracteres.")
	String endereco;


	private @NotBlank(message = "A cidade é obrigatória.")
	@Size(max = 50, message = "A cidade deve ter no máximo 50 caracteres.")
	String cidade;


	private  @NotBlank(message = "O estado (UF) é obrigatório.")
	@Size(min = 2, max = 2, message = "O estado (UF) deve ter 2 caracteres.")
	String uf;


	private @NotBlank(message = "O e-mail é obrigatório.")
	@Email(message = "O e-mail deve ser válido.")
	String email;


	private @NotBlank(message = "O telefone é obrigatório.")
	@Pattern(regexp = "\\d{11}", message = "O telefone deve ter 11 dígitos numéricos.")
	String telefone;


	private String senha;

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
