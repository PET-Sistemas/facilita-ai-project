package com.UFMSPetSistemas.getpet.model.repository;

import com.UFMSPetSistemas.getpet.model.entities.Categoria;
import com.UFMSPetSistemas.getpet.model.entities.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

    Servico findById(long id);

    List<Servico> findByCategoria(Categoria categoria);

    List<Servico> findByValorBetween(double minValor, double maxValor);

    List<Servico> findByUsuarioId(long usuarioId);

    List<Servico> findByUsuarioEndereco(String endereco);

}
