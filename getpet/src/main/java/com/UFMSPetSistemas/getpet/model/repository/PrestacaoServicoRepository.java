package com.UFMSPetSistemas.getpet.model.repository;


import com.UFMSPetSistemas.getpet.model.entities.PrestacaoServico;
import com.UFMSPetSistemas.getpet.model.entities.Usuario;
import com.UFMSPetSistemas.getpet.model.entities.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PrestacaoServicoRepository extends JpaRepository<PrestacaoServico, Long>{

    List<Servico> findByServicoId(Long id);
    @Query("SELECT p.servico FROM PrestacaoServico p WHERE p.avaliacao BETWEEN :avaliacaoMinima AND :avaliacaoMaxima")
    List<Servico> findServicosByAvaliacaoBetween(
            @Param("avaliacaoMinima") int avaliacaoMinima,
            @Param("avaliacaoMaxima") int avaliacaoMaxima
    );
    List<PrestacaoServico> findByUsuario(Long id);
}
