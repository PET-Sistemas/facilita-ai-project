package com.UFMSPetSistemas.getpet.model.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;

public class PrestacaoServico {

	private int id;

	private Date dataPrestacao;

	private int avaliacao;

	private String avaliacaoDescricao;

	private double valorServico;

	private Collection<UsuarioConsumidor> usuarioConsumidor;

	private int avaliacaoServico;

	private Collection<UsuarioServico> usuarioServico;

}
