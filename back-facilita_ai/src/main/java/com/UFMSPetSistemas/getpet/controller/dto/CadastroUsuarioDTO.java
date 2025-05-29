package com.UFMSPetSistemas.getpet.controller.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CadastroUsuarioDTO(    
    @JsonProperty("nomeCompleto") String nomeCompleto,
    @JsonProperty("dataNascimento") String dataNascimento,
    @JsonProperty("endereco") String endereco,
    @JsonProperty("cidade") String cidade,
    @JsonProperty("uf") String uf,
    @JsonProperty("email") String email,
    @JsonProperty("telefone") String telefone,
    @JsonProperty("senha") String senha
) {}