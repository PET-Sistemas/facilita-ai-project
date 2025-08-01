package com.UFMSPetSistemas.getpet.controller.categoria.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AtualizarCategoriaDTO (
        @JsonProperty("titulo") String titulo
) {
}