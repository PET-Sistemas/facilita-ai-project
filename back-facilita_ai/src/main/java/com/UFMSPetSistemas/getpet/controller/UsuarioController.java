package com.UFMSPetSistemas.getpet.controller;

import com.UFMSPetSistemas.getpet.model.entities.Usuario;
import com.UFMSPetSistemas.getpet.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> buscarPorId(final Long id) {
        try{
            final Usuario usuario = this.repo
                                        .findById(id)
                                        .orElseThrow(() -> new RuntimeException(String.format("Usuario com id %d não encontrado", id)));
            return ResponseEntity.ok(usuario);
        }catch (Exception e){
            System.out.println("Erro ao buscar por id: " + e.getMessage());
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID Inválido: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> buscarPorNome(final String nome) {
        try{
            final List<Usuario> usuarios = this.repo.findByNomeCompletoContaining(nome);

            return ResponseEntity.ok(usuarios);
        }catch (Exception e){
            System.out.println("Erro ao buscar por nome: " + e.getMessage());
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome Inválido: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> buscarPorEndereco(final String endereco) {
        try{
            final List<Usuario> usuarios = this.repo.findByEnderecoContaining(endereco);

            return ResponseEntity.ok(usuarios);
        }catch (Exception e){
            System.out.println("Erro ao buscar por endereço: " + e.getMessage());
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Endereco Inválido: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> putUsuario(final Usuario newColaborador, final Long id) {
        try {
            Usuario oldColaborador = this.repo.findById(id).isPresent() ? this.repo.findById(id).get() : new Usuario();
            //newColaborador.setId(oldColaborador.getId());

            oldColaborador.setNomeCompleto(newColaborador.getNomeCompleto());
            oldColaborador.setEmail(newColaborador.getEmail());
            oldColaborador.setTelefone(newColaborador.getTelefone());
            oldColaborador.setCidade(newColaborador.getCidade());
            oldColaborador.setEndereco(newColaborador.getEndereco());
            oldColaborador.setUf(newColaborador.getUf());
            oldColaborador.setDataNascimento(newColaborador.getDataNascimento());
            oldColaborador.setSenha(newColaborador.getSenha());

            if (!oldColaborador.getServicos().isEmpty()) {
                oldColaborador.getServicos().clear();
            }

            if (newColaborador.getServicos() != null) {
                oldColaborador.getServicos().addAll(newColaborador.getServicos());
            }

            if (!oldColaborador.getServicosPrestados().isEmpty()) {
                oldColaborador.getServicosPrestados().clear();
            }

            if (newColaborador.getServicosPrestados() != null) {
                oldColaborador.getServicosPrestados().addAll(newColaborador.getServicosPrestados());
            }

            if (!oldColaborador.getServicosContratados().isEmpty()) {
                oldColaborador.getServicosContratados().clear();
            }

            if (newColaborador.getServicosContratados() != null) {
                oldColaborador.getServicosContratados().addAll(newColaborador.getServicosContratados());
            }

            Usuario usuarioSalvo = this.repo.save(newColaborador);

            return ResponseEntity.ok().body(usuarioSalvo);
        } catch (Exception e) {
            System.err.println("Erro ao atualizar: " + e.getMessage());
            e.printStackTrace();

            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    @Override
    public void deleteColaborador(final Long id) {
        this.repo.deleteById(id);
    }
}
