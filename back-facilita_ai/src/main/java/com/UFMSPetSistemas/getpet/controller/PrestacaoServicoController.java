package com.UFMSPetSistemas.getpet.controller;

import com.UFMSPetSistemas.getpet.model.entities.PrestacaoServico;
import com.UFMSPetSistemas.getpet.model.entities.Servico;
import com.UFMSPetSistemas.getpet.model.repository.PrestacaoServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/prestacoes-servico")
public class PrestacaoServicoController {

    @Autowired
    private PrestacaoServicoRepository prestacaoServicoService;

    // Listar serviços por intervalo de avaliação
    @GetMapping("/avaliacoes")
    public List<Servico> listarPorAvaliacao(
            @RequestParam int avaliacaoMinima,
            @RequestParam int avaliacaoMaxima
    ) {
        return prestacaoServicoService.findServicosByAvaliacaoBetween(avaliacaoMinima, avaliacaoMaxima);
    }

    @GetMapping("/usuario/{id}")
    public List<PrestacaoServico> findByUsuario(@PathVariable Long id) {
        return prestacaoServicoService.findByUsuario(id);
    }
}
