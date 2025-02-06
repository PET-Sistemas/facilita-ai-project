package com.UFMSPetSistemas.getpet.model.repository;

import com.UFMSPetSistemas.getpet.model.entities.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    List<Usuario> findByNome(String nome);
    List<Usuario> findByEndereco(String endereco);
}