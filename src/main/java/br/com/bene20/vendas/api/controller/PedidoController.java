package br.com.bene20.vendas.api.controller;

import br.com.bene20.vendas.api.dto.AtualizacaoStatusPedidoDTO;
import br.com.bene20.vendas.api.dto.InformacoesPedidoDTO;
import br.com.bene20.vendas.api.dto.PedidoDTO;
import br.com.bene20.vendas.domain.entity.Pedido;
import br.com.bene20.vendas.domain.enums.StatusPedido;
import br.com.bene20.vendas.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {
    
    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO dto){
        Pedido registro = service.salvar(dto);
        return registro.getId();
    }
    
    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable("id") Integer id){
        return service
                .obterPedidoCompleto(id)
                .map(pedido -> InformacoesPedidoDTO.fromEntityPedido(pedido))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                                                               "Pedido não encontrado."));
    }
    
    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable("id") Integer id,
                             @RequestBody AtualizacaoStatusPedidoDTO dto){
        service.atualizaStatus(id, 
                         converteAtualizacaoStatusPedidoDTOToStatusPedido(dto));
    }

    private StatusPedido converteAtualizacaoStatusPedidoDTOToStatusPedido(AtualizacaoStatusPedidoDTO dto) throws ResponseStatusException {
        StatusPedido novoStatusPedido;
        try{
            novoStatusPedido = StatusPedido.valueOf(dto.getNovoStatus());
        } catch (java.lang.IllegalArgumentException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                                              "Status de pedido '"+dto.getNovoStatus()+"' é inválido.");
        }
        return novoStatusPedido;
    }
}
