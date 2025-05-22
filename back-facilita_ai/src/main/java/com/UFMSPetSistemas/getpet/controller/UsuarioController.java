package com.UFMSPetSistemas.getpet.controller;

import com.UFMSPetSistemas.getpet.model.entities.Usuario;
import com.UFMSPetSistemas.getpet.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin
public class UsuarioController implements IntUsuarioController {
    @Autowired
    private UsuarioRepository repo;

    @Override
    public ResponseEntity<?> cadastrarUsuario(final Usuario novoColaborador) {
        System.out.println("Dados recebidos: " + novoColaborador);
        System.out.println("Nome: " + novoColaborador.getNomeCompleto());
    
        try {
            Usuario usuarioSalvo = this.repo.save(novoColaborador);
            System.out.println("Usuário salvo: " + usuarioSalvo);
            
            return ResponseEntity.created(URI.create("/categories/" + usuarioSalvo.getId())).body(usuarioSalvo);
        } catch (Exception e) {
            System.err.println("Erro ao salvar: " + e.getMessage());
            e.printStackTrace();

            return ResponseEntity.unprocessableEntity().body(Map.of(
                    "errors", List.of(Map.of("message", e.getMessage()))
            ));
        }
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return this.repo.findAll();
    }

    @Override
    public Usuario buscarPorId(final Long id) {
        return this
            .repo
            .findById(id)
            .orElseThrow(() -> new RuntimeException(String.format("Usuario com id %d não encontrado", id)));
    }

    @Override
    public List<Usuario> buscarPorNome(final String nome) {
        return this.repo.findByNomeCompletoContaining(nome);
    }

    @Override
    public List<Usuario> buscarPorEndereco(final String endereco) {
        return this
                .repo
                .findByEnderecoContaining(endereco);
    }

    @Override
    public Usuario putUsuario(final Usuario newColaborador, final Long id) {
        Usuario oldColaborador = this.repo.findById(id).isPresent() ? this.repo.findById(id).get() : new Usuario();
        newColaborador.setId(oldColaborador.getId());
        return this.repo.save(newColaborador);
    }

    @Override
    public void deleteColaborador(final Long id) {
        this.repo.deleteById(id);
    }
}
