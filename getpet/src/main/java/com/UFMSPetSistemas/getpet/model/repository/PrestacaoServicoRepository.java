package com.UFMSPetSistemas.getpet.model.repository;
import com.UFMSPetSistemas.getpet.model.entities.PrestacaoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PrestacaoServicoRepository extends JpaRepository<PrestacaoServico, Long> {
    List<PrestacaoServico> findByAvaliacaoGreaterThanEqual(int avaliacao);
}

