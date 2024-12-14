package com.UFMSPetSistemas.getpet.model.entities;

import java.util.Collection;

public class TipoServico {

	private int id;

	private String nomeServico;

	private TipoServico tipoServico;

	private Collection<UsuarioFornecedor> usuarioServico;

	private Categoria categoria;

}
