package com.UFMSPetSistemas.getpet.controller;

import com.UFMSPetSistemas.getpet.model.entities.Usuario;
import com.UFMSPetSistemas.getpet.model.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
public class UsuarioController {
    UsuarioRepository repo;

    public UsuarioController(UsuarioRepository repo){
        this.repo = repo;
    }

    //@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(path = "/usuario")
    public Usuario insertColaborador(@RequestBody Usuario novoColaborador){
        System.out.println(novoColaborador);
        return this.repo.save(novoColaborador);
    }

    @GetMapping(path = "/usuario")
    public List<Usuario> getAll(){
        return this.repo.findAll();
    }

    @GetMapping(path = "/usuario/{id}")
    public Usuario getById(@PathVariable Long id){
        return this
                .repo
                .findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Usuario com id %d não encontrado", id)));
    }

    @PutMapping(path = "/usuario/{id}")
    public Usuario putUsuario(@RequestBody Usuario newColaborador, @PathVariable Long id){
        Usuario oldColaborador = this.repo.findById(id).isPresent() ? this.repo.findById(id).get() : new Usuario();
        newColaborador.setId(oldColaborador.getId());
        return this.repo.save(newColaborador);
    }

    @DeleteMapping("/usuario/{id}")
    void deleteColaborador(@PathVariable Long id) {
        this.repo.deleteById(id);
    }

}
