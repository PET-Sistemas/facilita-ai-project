package com.UFMSPetSistemas.getpet.controller.categoria.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record CadastroCategoriaDTO (
        @NotBlank(message = "O titulo é obrigatório.")
        @JsonProperty("titulo") String titulo
) {
}
