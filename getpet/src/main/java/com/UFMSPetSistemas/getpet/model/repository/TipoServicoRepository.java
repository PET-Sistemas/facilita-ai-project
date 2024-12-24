package com.UFMSPetSistemas.getpet.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.UFMSPetSistemas.getpet.model.entities.TipoServico;
import java.util.List;
import com.UFMSPetSistemas.getpet.model.entities.Categoria;
import com.UFMSPetSistemas.getpet.model.entities.PrestacaoServico; 

public interface TipoServicoRepository extends JpaRepository<PrestacaoServico, Long>{
    public List<TipoServico> findByCategoria(Categoria categoria);
}