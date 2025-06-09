package com.UFMSPetSistemas.getpet.controller.usuario.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record CadastroUsuarioDTO(
    @NotBlank(message = "O nome completo é obrigatório.")
    @JsonProperty("nomeCompleto") String nomeCompleto,

    @JsonProperty("dataNascimento") Date dataNascimento,

    @NotBlank(message = "O endereço é obrigatório.")
    @JsonProperty("endereco") String endereco,

    @NotBlank(message = "A cidade é obrigatória.")
    @JsonProperty("cidade") String cidade,

    @NotBlank(message = "O estado (UF) é obrigatório.")
    @JsonProperty("uf") String uf,

    @NotBlank(message = "O e-mail é obrigatório.")
    @JsonProperty("email") String email,

    @NotBlank(message = "O telefone é obrigatório.")
    @JsonProperty("telefone") String telefone,

    @JsonProperty("senha") String senha
) {}