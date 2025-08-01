package com.UFMSPetSistemas.getpet.controller.prestacaoServico;

import com.UFMSPetSistemas.getpet.controller.prestacaoServico.dto.AtualizarPrestacaoServicoDTO;
import com.UFMSPetSistemas.getpet.controller.prestacaoServico.dto.CadastrarPrestacaoServicoDTO;
import com.UFMSPetSistemas.getpet.controller.prestacaoServico.dto.PrestacaoServicoListDTO;
import com.UFMSPetSistemas.getpet.controller.servico.dto.ServicoSimplificadoDTO; // <-- Import com caminho padronizado
import com.UFMSPetSistemas.getpet.controller.usuario.dto.UsuarioSimplificadoDTO;   // <-- Import com caminho padronizado
import com.UFMSPetSistemas.getpet.model.entities.PrestacaoServico;
import com.UFMSPetSistemas.getpet.model.entities.Servico;
import com.UFMSPetSistemas.getpet.model.entities.Usuario;
import com.UFMSPetSistemas.getpet.model.repository.PrestacaoServicoRepository;
import com.UFMSPetSistemas.getpet.model.repository.ServicoRepository;
import com.UFMSPetSistemas.getpet.model.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class PrestacaoServicoController implements IntPrestacaoServicoController {

    @Autowired
    private PrestacaoServicoRepository prestacaoServicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ServicoRepository servicoRepository;

    @Override
    @Transactional
    public ResponseEntity<PrestacaoServico> registrar(@RequestBody @Valid CadastrarPrestacaoServicoDTO dto) {
        Usuario cliente = usuarioRepository.findById(dto.clienteId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + dto.clienteId()));
        Usuario prestador = usuarioRepository.findById(dto.prestadorId())
                .orElseThrow(() -> new EntityNotFoundException("Prestador não encontrado com ID: " + dto.prestadorId()));
        Servico servico = servicoRepository.findById(dto.servicoId())
                .orElseThrow(() -> new EntityNotFoundException("Serviço não encontrado com ID: " + dto.servicoId()));

        PrestacaoServico novaPrestacao = new PrestacaoServico();
        novaPrestacao.setUsuarioConsumidor(cliente);
        novaPrestacao.setUsuarioPrestador(prestador);
        novaPrestacao.setServico(servico);
        novaPrestacao.setDataprestacao(Date.from(dto.dataPrestacao().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        if (dto.avaliacao() != null) {
            novaPrestacao.setAvaliacao(dto.avaliacao());
        }

        PrestacaoServico prestacaoSalva = prestacaoServicoRepository.save(novaPrestacao);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(prestacaoSalva.getId()).toUri();

        return ResponseEntity.created(uri).body(prestacaoSalva);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<Servico>> listarPorAvaliacao(@RequestParam int avaliacaoMinima, @RequestParam int avaliacaoMaxima) {
        List<Servico> servicos = prestacaoServicoRepository.findServicosByAvaliacaoBetween(avaliacaoMinima, avaliacaoMaxima);
        return ResponseEntity.ok(servicos);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<PrestacaoServicoListDTO>> findByUsuarioPrestador(@PathVariable Long id) {
        List<PrestacaoServico> prestacoes = prestacaoServicoRepository.findByUsuarioPrestador(id);

        List<PrestacaoServicoListDTO> resultadoDTO = prestacoes.stream()
                .map(this::converterParaListDTO)
                .collect(Collectors.toList());
            
        return ResponseEntity.ok(resultadoDTO);
    }

    @Override
    @Transactional
    public ResponseEntity<PrestacaoServico> atualizarAvaliacao(@PathVariable Long id, @RequestBody @Valid AtualizarPrestacaoServicoDTO dto) {
        PrestacaoServico prestacao = prestacaoServicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prestação de Serviço não encontrada com ID: " + id));

        if (dto.avaliacao() != null) {
            prestacao.setAvaliacao(dto.avaliacao());
        }
        
        PrestacaoServico prestacaoAtualizada = prestacaoServicoRepository.save(prestacao);
        
        return ResponseEntity.ok(prestacaoAtualizada);
    }
    
    // Método auxiliar para converter a entidade em um DTO de lista
    private PrestacaoServicoListDTO converterParaListDTO(PrestacaoServico p) {
        ServicoSimplificadoDTO servicoDto = new ServicoSimplificadoDTO(
                p.getServico().getId(),
                p.getServico().getTitulo()
        );

        UsuarioSimplificadoDTO clienteDto = new UsuarioSimplificadoDTO(
                p.getUsuarioConsumidor().getId(),
                p.getUsuarioConsumidor().getNomeCompleto()
        );

        return new PrestacaoServicoListDTO(
                p.getId(),
                p.getDataprestacao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                p.getAvaliacao(),
                servicoDto,
                clienteDto
        );
    }
}