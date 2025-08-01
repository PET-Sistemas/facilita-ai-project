package com.UFMSPetSistemas.getpet.controller.prestacaoServico;

import com.UFMSPetSistemas.getpet.controller.prestacaoServico.dto.AtualizarPrestacaoServicoDTO;
import com.UFMSPetSistemas.getpet.controller.prestacaoServico.dto.CadastrarPrestacaoServicoDTO;
import com.UFMSPetSistemas.getpet.controller.prestacaoServico.dto.PrestacaoServicoListDTO;
import com.UFMSPetSistemas.getpet.model.entities.PrestacaoServico;
import com.UFMSPetSistemas.getpet.model.entities.Servico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/prestacoes-servico")
@Tag(name = "Prestação de Serviços", description = "Endpoints para o gerenciamento e consulta de prestações de serviço")
public interface IntPrestacaoServicoController {

    @PostMapping
    @Operation(summary = "Registrar uma nova prestação de serviço")
    @ApiResponse(responseCode = "201", description = "Prestação de serviço registrada com sucesso", content = @Content(schema = @Schema(implementation = PrestacaoServico.class)))
    ResponseEntity<PrestacaoServico> registrar(@RequestBody @Valid CadastrarPrestacaoServicoDTO dto);


    @GetMapping("/avaliacoes")
    @Operation(summary = "Listar serviços por faixa de avaliação")
    @ApiResponse(responseCode = "200", description = "Lista de serviços encontrada", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Servico.class))))
    ResponseEntity<List<Servico>> listarPorAvaliacao(
            @Parameter(in = ParameterIn.QUERY, name = "avaliacaoMinima") int avaliacaoMinima,
            @Parameter(in = ParameterIn.QUERY, name = "avaliacaoMaxima") int avaliacaoMaxima);


    @GetMapping("/usuario/{id}")
    @Operation(summary = "Buscar prestações de serviço por ID do prestador")
    @ApiResponse(responseCode = "200", description = "Lista simplificada de prestações de serviço encontrada",
        content = @Content(mediaType = "application/json",
        array = @ArraySchema(schema = @Schema(implementation = PrestacaoServicoListDTO.class))))
    @ApiResponse(responseCode = "404", description = "Usuário prestador não encontrado", content = @Content)
    ResponseEntity<List<PrestacaoServicoListDTO>> findByUsuarioPrestador(
            @Parameter(in = ParameterIn.PATH, name = "id") Long id);


    @PatchMapping("/{id}/avaliacao")
    @Operation(summary = "Atualizar avaliação de um serviço prestado")
    @ApiResponse(responseCode = "200", description = "Avaliação atualizada com sucesso", content = @Content(schema = @Schema(implementation = PrestacaoServico.class)))
    ResponseEntity<PrestacaoServico> atualizarAvaliacao(
            @Parameter(in = ParameterIn.PATH, name = "id") Long id,
            @RequestBody @Valid AtualizarPrestacaoServicoDTO dto);
}