package com.UFMSPetSistemas.getpet.model.repository;


import com.UFMSPetSistemas.getpet.model.entities.PrestacaoServico;
import com.UFMSPetSistemas.getpet.model.entities.Usuario;
import com.UFMSPetSistemas.getpet.model.entities.Servico;
//vai estender da interface repository Jpa
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface PrestacaoServicoRepository extends JpaRepository<PrestacaoServico, Long>{
    //retorna uma lista de objetos do tipo servico 
    // busca as prestações de serviçoID de serviço
    //o método recebe o id de Servico como parâmetro 
    List<Servico> findByServicoId(Long id);  
  
    //retorna uma lista de objetos do tipo prestacao de servico
    //busca prestações de serviço com avaliação maior ou igual a um determinado valor.
    //o método recebe uma avaliacao  da prestacao de servico como parâmetro
    List<PrestacaoServico> findByAvaliacaoGreaterThanEqual(int avaliacao); 
    
    //o método recebe um ID de usuário como parâmetro
    ////retorna uma lista de objetos do tipo prestacao de servico
    //busca todas as prestações de serviço associadas a um determinado usuário
    List<PrestacaoServico> findByUsuario(Long id); 
}
