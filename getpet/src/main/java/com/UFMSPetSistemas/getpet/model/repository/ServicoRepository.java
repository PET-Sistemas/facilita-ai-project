package com.UFMSPetSistemas.getpet.model.repository;

import com.UFMSPetSistemas.getpet.model.entities.PrestacaoServico;
import com.UFMSPetSistemas.getpet.model.entities.TipoServico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<PrestacaoServico, Long>{

    public List<PrestacaoServico> findByValorServico(Double ValorServico);
}
