package com.UFMSPetSistemas.getpet.model.entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Categoria {
    @Id
    @GeneratedValue
    private Long id;

    private String titulo;

    public Categoria() {
    }

    public Categoria(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(id, categoria.id) &&
               Objects.equals(titulo, categoria.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo);
    }
}
