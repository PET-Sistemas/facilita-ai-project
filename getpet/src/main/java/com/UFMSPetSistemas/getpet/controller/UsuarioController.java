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
    public Usuario cadastrarUsuario(@RequestBody Usuario novoColaborador){
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
    public List<Usuario> listarUsuarios(){
        return this.repo.findAll();
    }

    @GetMapping //api/usuario?id={var}
    public Usuario buscarPorId(@RequestParam Long id){
        return this
                .repo
                .findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Usuario com id %d não encontrado", id)));
    }

    @GetMapping //api/usuario?nome={var}
    public List<Usuario> buscarPorNome(@RequestParam String nome){
        return this
                .repo
                .findByNome(nome);
    }

    @PutMapping //api/usuario?id={var}
    public Usuario putUsuario(@RequestBody Usuario newColaborador, @RequestParam Long id){
        Usuario oldColaborador = this.repo.findById(id).isPresent() ? this.repo.findById(id).get() : new Usuario();
        newColaborador.setId(oldColaborador.getId());
        return this.repo.save(newColaborador);
    }

    @DeleteMapping //api/usuario?id={var}
    void deleteColaborador(@RequestParam Long id) {
        this.repo.deleteById(id);
    }

    @GetMapping //api/usuario?endereco={var}
    public List<Usuario> buscarPorEndereco(@RequestParam String endereco){
        return this
                .repo
                .findByEndereco(endereco);
    }
}
