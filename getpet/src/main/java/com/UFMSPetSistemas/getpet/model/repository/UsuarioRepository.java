package com.UFMSPetSistemas.getpet.model.repository;


import com.UFMSPetSistemas.getpet.model.entities.Usuario;
//vai estender da interface repository Jpa
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    List<Usuario> findByEmail(String email);

    List<Usuario> findByEnderecoContaining(String endereco);

    //List<Usuario> findById(Long id);

    List<Usuario> findByNomeContaining(String nome);

    void deleteById(Long id);
    

    
}
