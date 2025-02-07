package com.UFMSPetSistemas.getpet.model.repository;


import com.UFMSPetSistemas.getpet.model.entities.Usuario;
//vai estender da interface repository Jpa
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    //o método retornará uma lista de objetos do tipo Usuário
    // busca por Usuarios cujo campo email seja igual ao valor fornecido como argumento
    List<Usuario> findByEmail(String email); 

    //o método retornará uma lista de objetos do tipo Usuário
    //busca por Usuarios  cujo campo endereco contenha a substring fornecida
    List<Usuario> findByEnderecoContaining(String endereco);

    //o método retornará uma lista de objetos do tipo Usuário (ta certo isso? , não deveria ser somente um objeto?)
    //busca um registro específico no banco de dados utilizando o ID
    List<Usuario> findById(Long id); 

    //o método retornará uma lista de objetos do tipo Usuário
    //busca por Usuarios  cujo campo nome contenha a substring fornecida
    List<Usuario> findByNomeContaining(String nome);

    //o método não retorna nenhum valor
    // deleta um registro do banco de dados com base no (id) que é fornecido como parâmetro
    // com isso, no banco de dados o registro será deletado
    void deleteById(Long id);
    

    
}
