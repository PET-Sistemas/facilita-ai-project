package com.UFMSPetSistemas.getpet.controller;

import com.UFMSPetSistemas.getpet.model.entities.Usuario;
import com.UFMSPetSistemas.getpet.model.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/usuario")
public class UsuarioController {
    private UsuarioRepository repo;

    @PostMapping(path = "/")
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
    public List<Usuario> listarUsuarios() {
        return this.repo.findAll();
    }

    @GetMapping(path = "/id") 
    public Usuario buscarPorId(@RequestParam Long id) {
        return this
                .repo
                .findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Usuario com id %d não encontrado", id)));
    }

    @GetMapping(path = "/nome")
    public List<Usuario> buscarPorNome(@RequestParam String nome) {
        return this.repo.findByNomeCompletoContaining(nome);
    }

    @PutMapping(path = "/") 
    public Usuario putUsuario(@RequestBody Usuario newColaborador, @RequestParam Long id) {
        Usuario oldColaborador = this.repo.findById(id).isPresent() ? this.repo.findById(id).get() : new Usuario();
        newColaborador.setId(oldColaborador.getId());
        return this.repo.save(newColaborador);
    }

    @DeleteMapping(path = "/") 
    void deleteColaborador(@RequestParam Long id) {
        this.repo.deleteById(id);
    }

    @GetMapping(path = "/endereco") 
    public List<Usuario> buscarPorEndereco(@RequestParam String endereco) {
        return this
                .repo
                .findByEnderecoContaining(endereco);
    }
}
