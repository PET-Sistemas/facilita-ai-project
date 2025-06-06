package com.UFMSPetSistemas.getpet.controller;

import com.UFMSPetSistemas.getpet.model.entities.Usuario;
import com.UFMSPetSistemas.getpet.model.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/usuario")
@Tag(name = "Usuário", description = "Endpoints para gerenciamento de usuários")
public class UsuarioController {
    private UsuarioRepository repo;

    @PostMapping(path = "/")
    @Operation(summary = "Cadastrar um novo usuário", description = "Recebe os dados de um novo usuário e o salva no banco de dados.")
    public Usuario cadastrarUsuario(@RequestBody Usuario novoColaborador) {
        System.out.println("Dados recebidos: " + novoColaborador);
        try {
            Usuario usuarioSalvo = this.repo.save(novoColaborador);
            System.out.println("Usuário salvo: " + usuarioSalvo);
            return usuarioSalvo;
        } catch (Exception e) {
            System.err.println("Erro ao salvar: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping(path = "/todos")
    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista com todos os usuários cadastrados.")
    public List<Usuario> listarUsuarios() {
        return this.repo.findAll();
    }

    @GetMapping(path = "/id")
    @Operation(summary = "Buscar usuário por ID", description = "Busca um usuário pelo seu ID.")
    public Usuario buscarPorId(
            @RequestParam
            @Parameter(description = "ID do usuário a ser buscado") Long id) {
        return this
                .repo
                .findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Usuario com id %d não encontrado", id)));
    }

    @GetMapping(path = "/nome")
    @Operation(summary = "Buscar usuários por nome", description = "Busca usuários cujo nome contenha o valor informado.")
    public List<Usuario> buscarPorNome(
            @RequestParam
            @Parameter(description = "Nome ou parte do nome do usuário") String nome) {
        return this.repo.findByNomeCompletoContaining(nome);
    }

    @PutMapping(path = "/")
    @Operation(summary = "Atualizar um usuário", description = "Atualiza os dados de um usuário existente.")
    public Usuario putUsuario(
            @RequestBody Usuario newColaborador,
            @RequestParam
            @Parameter(description = "ID do usuário a ser atualizado") Long id) {
        Usuario oldColaborador = this.repo.findById(id).isPresent() ? this.repo.findById(id).get() : new Usuario();
        newColaborador.setId(oldColaborador.getId());
        return this.repo.save(newColaborador);
    }

    @DeleteMapping(path = "/")
    @Operation(summary = "Deletar um usuário", description = "Remove um usuário do banco de dados pelo ID.")
    public void deleteColaborador(
            @RequestParam
            @Parameter(description = "ID do usuário a ser deletado") Long id) {
        this.repo.deleteById(id);
    }

    @GetMapping(path = "/endereco")
    @Operation(summary = "Buscar usuários por endereço", description = "Busca usuários cujo endereço contenha o valor informado.")
    public List<Usuario> buscarPorEndereco(
            @RequestParam
            @Parameter(description = "Endereço ou parte do endereço do usuário") String endereco) {
        return this
                .repo
                .findByEnderecoContaining(endereco);
    }
}
