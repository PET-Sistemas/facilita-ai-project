package com.UFMSPetSistemas.getpet.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UFMSPetSistemas.getpet.model.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    List<Categoria> findByTituloContainingIgnoreCase(String titulo);
}
