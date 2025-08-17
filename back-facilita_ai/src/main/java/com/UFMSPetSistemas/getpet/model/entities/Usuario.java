package com.UFMSPetSistemas.getpet.model.entities;

import com.UFMSPetSistemas.getpet.model.entities.Servico;
import com.UFMSPetSistemas.getpet.model.entities.PrestacaoServico;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Entity
public class Usuario {
	/* ATRIBUTOS */
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private String nomeCompleto;

    @Temporal(TemporalType.DATE)
	private Date dataNascimento;

	private String endereco;

	private String cidade;

	private String uf;

	private String email;

	@Pattern(regexp = "\\d{11}", message = "O telefone deve ter 11 dígitos numéricos.")
	private String telefone;

	private String senha;

//	@OneToMany(mappedBy = "usuarioPrestador", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Servico> servicos;
//
//	@OneToMany(mappedBy = "usuarioPrestador", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<PrestacaoServico> servicosPrestados;
//
//	@OneToMany(mappedBy = "usuarioConsumidor", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<PrestacaoServico> servicosContratados;

	/* CONSTRUTORES */
	public Usuario(String nomeCompleto,
				   Date dataNascimento,
				   String endereco,
				   String cidade,
				   String uf,
				   String email,
				   String telefone,
				   String senha
	) {
		this.nomeCompleto = nomeCompleto;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.cidade = cidade;
		this.uf = uf;
		this.email = email;
		this.telefone = telefone;
		setSenha(senha);
//		this.servicos = null;
//		this.servicosPrestados = null;
//		this.servicosContratados = null;
	}

	public Usuario(){} // Construtor sem argumentos para o framework

	/**
	 * Factory Method para criar novo Usuario quando construtor for privado
	 *
	 */
	public static Usuario newUsuario() {
		System.out.println("Não implementado");

		return new Usuario();
	}

	/* GETTERS */
	public Long getId() {
		return id;
	}

//	public List<Servico> getServicos() {
//		return servicos;
//	}
//
//	public List<PrestacaoServico> getServicosPrestados() {
//		return servicosPrestados;
//	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public String getUf() {
		return uf;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getSenha() {
		return senha;
	}

//	public List<PrestacaoServico> getServicosContratados() {
//		return servicosContratados;
//	}

	/* SETTERS */
//	public void setServicos(List<Servico> servicos) {
//		this.servicos = servicos;
//	}
//
//	public void setServicosPrestados(List<PrestacaoServico> servicosPrestados) {
//		this.servicosPrestados = servicosPrestados;
//	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	//Criptografa a senha antes de setar
	public void setSenha(String senha) {
		//verifca se a senha não é nula ou vazia
		if(senha != null && !senha.isEmpty()){ 
			//Criar uma instância do BCryptPasswordEncoder
			//objeto responsável por criptografar a senha
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			//o método encode gera um hash da senha e armazena esse hash no atributo senha do usuário e salva no banco
			this.senha = encoder.encode(senha);
		} 
	}

//	public void setServicosContratados(List<PrestacaoServico> servicosConsumidos) {
//		this.servicosContratados = servicosConsumidos;
//	}

	/* MÉTODOS DA CLASSE */

	public Usuario update(
		String nomeCompleto,
		Date dataNascimento,
		String endereco,
		String cidade,
		String uf,
		String email,
		String telefone,
		String senha
	){
		setNomeCompleto(nomeCompleto);
		setDataNascimento(dataNascimento);
		setEndereco(endereco);
		setCidade(cidade);
		setUf(uf);
		setEmail(email);
		setTelefone(telefone);
		setSenha(senha);

		return this;
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