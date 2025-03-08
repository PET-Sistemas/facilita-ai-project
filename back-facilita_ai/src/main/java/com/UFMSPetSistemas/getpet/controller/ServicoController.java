// As dependencias nao vao funcionar dado que as classes de entidade e repositorio de  Usuario e Categoria ainda nao foram desevolvidas

package com.UFMSPetSistemas.getpet.controller;

import com.UFMSPetSistemas.getpet.model.entities.Usuario;
import com.UFMSPetSistemas.getpet.model.entities.Servico;
import com.UFMSPetSistemas.getpet.model.entities.Categoria;
import com.UFMSPetSistemas.getpet.model.repository.ServicoRepository;
import com.UFMSPetSistemas.getpet.model.repository.UsuarioRepository;
import com.UFMSPetSistemas.getpet.model.repository.CategoriaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    private final ServicoRepository servicoRepository;
    private final CategoriaRepository categoriaRepository;

    public ServicoController(ServicoRepository servicoRepository, CategoriaRepository categoriaRepository) {
        this.servicoRepository = servicoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    // Buscar todos os serviços
    @GetMapping
    public List<Servico> getAllServicos() {
        return servicoRepository.findAll();
    }

    // Buscar serviço por ID
    @GetMapping("/{id}")
    public ResponseEntity<Servico> getServicoById(@PathVariable Long id) {
        Optional<Servico> servico = servicoRepository.findById(id);
        return servico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Buscar serviços por endereço do usuário
    @GetMapping("/usuario-endereco")
    @Transactional(readOnly = true)
    public List<Servico> getServicosByUsuarioEndereco(@RequestParam String endereco) {
        return servicoRepository.findByUsuarioEndereco(endereco);
    }

    // Buscar serviços por categoria
    @GetMapping("/categoria/{id}")
    @Transactional(readOnly = true)
    public List<Servico> getServicosByCategoria(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.map(servicoRepository::findByCategoria).orElse(List.of());
    }

    // Buscar serviços por valor entre um intervalo
    @GetMapping("/valor")
    public List<Servico> getServicosByValor(@RequestParam double minValor, @RequestParam double maxValor) {
        return servicoRepository.findByValorBetween(minValor, maxValor);
    }

    // Criar novo serviço
    @PostMapping
    @Transactional
    public ResponseEntity<Servico> createServico(@RequestBody Servico servico) {
        // Verificar se o ID da categoria foi enviado e se é válido
        if (servico.getCategoria() == null || servico.getCategoria().getId() == null) {
            return ResponseEntity.badRequest().body(null); // Categoria não informada ou inválida
        }

        Optional<Categoria> categoria = categoriaRepository.findById(servico.getCategoria().getId());
        if (categoria.isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Categoria não encontrada
        }

        // Atribuir a categoria carregada ao serviço
        servico.setCategoria(categoria.get());

        // Salvar o serviço no banco
        Servico novoServico = servicoRepository.save(servico);
        return ResponseEntity.ok(novoServico);
    }


    // Atualizar serviço (suporte a atualização parcial)
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Servico> updateServico(@PathVariable Long id, @RequestBody Servico servicoAtualizado) {
        Optional<Servico> servicoExistente = servicoRepository.findById(id);

        if (servicoExistente.isPresent()) {
            Servico servico = servicoExistente.get();

            // Atualizar apenas os atributos enviados
            if (servicoAtualizado.getTitulo() != null) {
                servico.setTitulo(servicoAtualizado.getTitulo());
            }
            if (servicoAtualizado.getDescricao() != null) {
                servico.setDescricao(servicoAtualizado.getDescricao());
            }
            if (servicoAtualizado.getValor() != 0) {
                servico.setValor(servicoAtualizado.getValor());
            }
            if (servicoAtualizado.getCategoria() != null && servicoAtualizado.getCategoria().getId() != null) {
                Optional<Categoria> categoria = categoriaRepository.findById(servicoAtualizado.getCategoria().getId());
                if (categoria.isPresent()) {
                    servico.setCategoria(categoria.get());
                } else {
                    return ResponseEntity.badRequest().body(null);
                }
            }

            // Salvar alterações
            servicoRepository.save(servico);
            return ResponseEntity.ok(servico);
        }

        return ResponseEntity.notFound().build();
    }

    // Deletar serviço
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServico(@PathVariable Long id) {
        if (servicoRepository.existsById(id)) {
            servicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}


