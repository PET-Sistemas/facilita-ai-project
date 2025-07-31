package com.UFMSPetSistemas.getpet.controller.servico.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AtualizarServicoDTO(
        @JsonProperty("titulo") String titulo,

        @JsonProperty("descricao") String descricao,

        @JsonProperty("valor") Double valor,

        @JsonProperty("categoriaId") Long categoriaID,

        @JsonProperty("usuarioPrestadorId") Long usuarioPrestadorID
//
//        @JsonProperty("usuarioConsumidorId") Long usuarioConsumidorID
) {
}
