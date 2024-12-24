
package com.UFMSPetSistemas.getpet.controller;


import com.UFMSPetSistemas.getpet.model.entities.PrestacaoServico;
import com.UFMSPetSistemas.getpet.model.entities.Usuario;
import com.UFMSPetSistemas.getpet.model.repository.ServicoRepository;
import com.UFMSPetSistemas.getpet.model.repository.TipoServicoRepository;
import com.UFMSPetSistemas.getpet.model.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@CrossOrigin
public class ServicoController {


    private final ServicoRepository repo; // Criar um repositório específico para serviço
    private final TipoServicoRepository tipoServicoRepository;


    public ServicoController(ServicoRepository repo) {
        this.repo = repo;
    }


    @GetMapping(path = "/servico/{id}")
    public PrestacaoServico getById(@PathVariable Long id) {
        return this.repo.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Serviço com id %d não encontrado", id)));
    }


    @PutMapping(path = "/servico/{id}")
    public PrestacaoServico putServico(@RequestBody PrestacaoServico newServico, @PathVariable Long id) {
        PrestacaoServico oldServico = this.repo.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Serviço com id %d não encontrado", id)));
        newServico.setId(oldServico.getId()); // Atualiza o ID para manter a referência
        return this.repo.save(newServico);
    }


    @DeleteMapping("/servico/{id}")
    public void deleteServico(@PathVariable Long id) {
        if (!this.repo.existsById(id)) {
            throw new RuntimeException(String.format("Serviço com id %d não encontrado", id));
        }
        this.repo.deleteById(id);
    }


    @GetMapping(path = "/servico/tipo/{tipo}")
    public List<PrestacaoServico> getServicoByTipo(@PathVariable String categoria) {
        // fazer find categoria by name, e retornar um tipo de servico pela categoria com o método findByTipoServico
        return this.repo.findByTipoServico(tipo);
    }


    @GetMapping(path = "/servico/preco/{preco}")
    public List<PrestacaoServico> getServicoByPreco(@PathVariable Double preco) {
        return this.repo.findByValorServico(preco);
    }

    // Não implementável com o schema de banco que temos hoje
    // @GetMapping(path = "/servico/prazo/{prazo}")
    // public List<PrestacaoServico> getServicoByPrazo(@PathVariable String prazo) {
    //     return this.repo.findByPrazo(prazo);
    // }


    @PostMapping(path = "/servico")
    public PrestacaoServico setServico(@RequestBody PrestacaoServico novoServico) {
        return this.repo.save(novoServico);
    }
}


