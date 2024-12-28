package com.UFMSPetSistemas.getpet.controller;

import com.UFMSPetSistemas.getpet.model.entities.Usuario;
import com.UFMSPetSistemas.getpet.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository repo;

    @Autowired
    public AuthController(UsuarioRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/login")
    public ResponseEntity<String> verificaLogin(@RequestBody Usuario usuarioReq) {
        List<Usuario> usuarios = repo.findAll();
        Usuario req = repo.findByEmail(usuarioReq.getEmail());

        if (usuarios.isEmpty()) { 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista de usuários vazia");
        }
        
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail() == req.getEmail() && usuario.getSenha().equals(req.getSenha())) {
                return ResponseEntity.ok("Usuário autorizado.");
            }
        }
    
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não tem permissão para acessar esse recurso.");
    }
    
}
