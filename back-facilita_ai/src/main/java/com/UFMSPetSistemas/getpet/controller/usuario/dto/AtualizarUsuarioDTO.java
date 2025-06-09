package com.UFMSPetSistemas.getpet.controller.usuario.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record AtualizarUsuarioDTO(
        @JsonProperty("nomeCompleto") String nomeCompleto,

        @JsonProperty("dataNascimento") Date dataNascimento,

        @JsonProperty("endereco") String endereco,

        @JsonProperty("cidade") String cidade,

        @JsonProperty("uf") String uf,

        @JsonProperty("email") String email,

        @JsonProperty("telefone") String telefone,

        @JsonProperty("senha") String senha
) {
}
