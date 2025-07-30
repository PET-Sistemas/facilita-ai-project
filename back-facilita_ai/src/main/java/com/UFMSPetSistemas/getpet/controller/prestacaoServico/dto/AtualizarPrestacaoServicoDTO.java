package com.UFMSPetSistemas.getpet.controller.prestacaoServico.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Schema(description = "DTO para atualizar a avaliação de uma prestação de serviço existente.")
public record AtualizarPrestacaoServicoDTO(

    @Min(value = 1, message = "A avaliação mínima é 1.")
    @Max(value = 5, message = "A avaliação máxima é 5.")
    @Schema(description = "Nova nota de avaliação do serviço (de 1 a 5).", example = "4")
    Integer avaliacao,

    @Schema(description = "Novo comentário da avaliação.", example = "Gostei, mas poderia ser melhor.")
    String avaliacaodesc
) {}