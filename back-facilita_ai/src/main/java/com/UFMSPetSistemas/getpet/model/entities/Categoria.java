package com.UFMSPetSistemas.getpet.model.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity
public class Categoria{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @NotBlank(message = " O título deve ser informado")
    private String titulo;

    //métodos gets e sets
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getTitulo(){
        return titulo;
    }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
}
