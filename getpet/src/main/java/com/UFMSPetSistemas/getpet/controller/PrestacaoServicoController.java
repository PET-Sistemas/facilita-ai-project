package com.UFMSPetSistemas.getpet.controller;

import com.UFMSPetSistemas.getpet.model.entities.Servico;
import com.UFMSPetSistemas.getpet.model.entities.PrestacaoServico;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//import java.util.Optional;


//indica que a classe é um controlador rest capaz de receber requisições HTTP e retornar respostas
@RestController 
//aqui pode mudar dependendo da url
//define o mapeamento base para todas as urls relacionadas a serviço
@RequestMapping("/api/servicos")
//classe controller
public class PrestacaoServicoController{

    //injeção de dependencia do serviço (não sei se possui a camada serviço, então ajustar)
    //tive dificuldade no desenvolvimento
    @Autowired
    private PrestacaoServicoService prestacaoServicoService; 

    //mapeia requisições GET para o endpoint / servico / avaliacao
    @GetMapping("/avaliacao")
    //esse método recebe como parâmetro a avaliação mínima e máxima 
    // tem como retorno uma lista de objetos do tipo Servico que se encaixam nesses intervalo de avaliacao informado
    public List<Servico> listarPorAvalicao( @RequestParam("min") int avalicaoMinima, @ResquestParam("max") int avaliacaoMaxima){
       /*não sei se está correto */
       return prestacaoServicoService.listarPorAvalicao(avalicaoMinima,avaliacaoMaxima);
    }

    //mapeia requisições GET para o endpoint / servico / usuario/ {id}
    @GetMapping("/usuario/{id}")
    //esse método recebe como parâmetro o id de um Usuario
    //tem como retorno uma lista de objetos de Prestacao de Servico de acordo com esse id do usuario informado
    public List<PrestacaoServico> buscarPorUsuario(@PathVariable Long id){ // extrai o valor do parâmetro {id} da url para o parâmetro
        /*não sei se está correto */
        return prestacaoServicoService.buscarPorUsuario(id);
    }

}
