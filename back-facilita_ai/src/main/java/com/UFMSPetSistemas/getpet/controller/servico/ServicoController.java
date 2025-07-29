package com.UFMSPetSistemas.getpet.controller.servico;

import com.UFMSPetSistemas.getpet.controller.servico.dto.CadastroServicoDTO;
import com.UFMSPetSistemas.getpet.model.entities.Servico;
import com.UFMSPetSistemas.getpet.model.entities.Categoria;
import com.UFMSPetSistemas.getpet.model.entities.Usuario;
import com.UFMSPetSistemas.getpet.model.repository.ServicoRepository;
import com.UFMSPetSistemas.getpet.model.repository.CategoriaRepository;
import com.UFMSPetSistemas.getpet.model.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/servico")
public class ServicoController implements IntServicoController{
    private final ServicoRepository servicoRepository;
    private final CategoriaRepository categoriaRepository;
    private final UsuarioRepository usuarioRepository;

    public ServicoController(ServicoRepository servicoRepository, CategoriaRepository categoriaRepository, UsuarioRepository usuarioRepository) {
        this.servicoRepository = servicoRepository;
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public ResponseEntity<?> createServico(@RequestBody CadastroServicoDTO servicoDTO) {
        System.out.println("Dados recebidos: " + servicoDTO);

        try {
            // Verificar se o ID da categoria foi enviado e se é válido
            if (servicoDTO.categoriaID() == null || servicoDTO.categoriaID() == null) {
                return ResponseEntity.badRequest().body(null); // Categoria não informada ou inválida
            }

            Optional<Categoria> categoria = categoriaRepository.findById(servicoDTO.categoriaID());

            if (categoria.isEmpty()) {
                return ResponseEntity.badRequest().body("Categoria não encontrada!"); // Categoria não encontrada
            }

            Optional<Usuario> usuarioPrestador = usuarioRepository.findById(servicoDTO.usuarioPrestadorID());

            if(usuarioPrestador.isEmpty()){
                return ResponseEntity.badRequest().body("Usuario prestador não encontrado!"); // Usuario não encontrado
            }

            Optional<Usuario> usuarioConsumidor = usuarioRepository.findById(servicoDTO.usuarioConsumidorID());

            if(usuarioConsumidor.isEmpty()){
                return ResponseEntity.badRequest().body("Usuario consumidor não encontrado!"); // Usuario não encontrado
            }

            Servico servicoSalvo = this.servicoRepository.save(new Servico(
                    servicoDTO.titulo(),
                    servicoDTO.descricao(),
                    servicoDTO.valor(),
                    categoria.get(),
                    usuarioPrestador.get(),
                    usuarioConsumidor.get()
            ));

            System.out.println("Servico salvo: " + servicoSalvo);

            return ResponseEntity.created(URI.create("/categories/" + servicoSalvo.getId())).body(servicoSalvo);
        } catch (Exception e) {
            System.err.println("Erro ao salvar: " + e.getMessage());
            e.printStackTrace();

            return ResponseEntity.unprocessableEntity().body(Map.of(
                    "errors", List.of(Map.of("message", e.getMessage()))
            ));
        }
    }

    @Override
    public List<Servico> getAllServicos() {
        return servicoRepository.findAll();
    }

    @Override
    public ResponseEntity<Servico> getServicoById(Long id) {
        Optional<Servico> servico = servicoRepository.findById(id);
        return servico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public List<Servico> getServicosByUsuarioPrestadorEndereco(@RequestParam String endereco) {
        return servicoRepository.findByUsuarioPrestadorEndereco(endereco);
    }

    @Override
    public List<Servico> getServicosByCategoria(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.map(servicoRepository::findByCategoria).orElse(List.of());
    }

    @Override
    public List<Servico> getServicosByValor(@RequestParam double minValor, @RequestParam double maxValor) {
        return servicoRepository.findByValorBetween(minValor, maxValor);
    }

    @Override
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

    @Override
    public ResponseEntity<Void> deleteServico(@PathVariable Long id) {
        if (servicoRepository.existsById(id)) {
            servicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}


