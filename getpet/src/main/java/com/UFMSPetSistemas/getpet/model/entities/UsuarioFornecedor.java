package com.UFMSPetSistemas.getpet.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.Collection;

@Entity
public class UsuarioFornecedor extends Usuario {

    @OneToMany(mappedBy = "usuarioFornecedor")
    private Collection<TipoServico> usuarioServico;

    // Getters e Setters
    public Collection<TipoServico> getUsuarioServico() {
        return usuarioServico;
    }

    public void setUsuarioServico(Collection<TipoServico> usuarioServico) {
        this.usuarioServico = usuarioServico;
    }
}
