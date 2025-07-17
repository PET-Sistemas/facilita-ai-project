package com.UFMSPetSistemas.getpet.controller.servico.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record CadastroServicoDTO(
        @NotBlank(message = "O titulo é obrigatório.")
        @JsonProperty("titulo") String titulo,

        @JsonProperty("descricao") String descricao,

        @NotBlank(message = "O valor é obrigatório.")
        @JsonProperty("valor") Double valor,

        @NotBlank(message = "O id de categoria é obrigatória.")
        @JsonProperty("categoriaId") Long categoriaID,

        @NotBlank(message = "O id de usuário prestador é obrigatório.")
        @JsonProperty("usuarioPrestadorId") Long usuarioPrestadorID,

        @NotBlank(message = "O id de usuário consumidor é obrigatório.")
        @JsonProperty("usuarioConsumidorId") Long usuarioConsumidorID
) {}