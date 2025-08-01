package com.UFMSPetSistemas.getpet.controller.prestacaoServico.dto;
import com.UFMSPetSistemas.getpet.controller.servico.dto.ServicoSimplificadoDTO;
import com.UFMSPetSistemas.getpet.controller.usuario.dto.UsuarioSimplificadoDTO;
import java.time.LocalDate;

public record PrestacaoServicoListDTO(
    Long id,
    LocalDate dataPrestacao,
    int avaliacao,
    ServicoSimplificadoDTO servico,
    UsuarioSimplificadoDTO cliente
) {}