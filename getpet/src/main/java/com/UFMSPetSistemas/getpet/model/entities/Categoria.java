package com.UFMSPetSistemas.getpet.model.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


//anotação que mostra que essa classe é uma entidade que pode ser persistida no bd
@Entity
public class Categoria{
    @Id // anotação que específica que esse atributo é uma chave primária no banco de Dados
    //anotação que gera automaticamente os ids
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    //validações para esse atributo
    // não se é necessário e se foi pedido isso
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
