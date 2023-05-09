package br.com.bene20.vendas.api.controller;

import br.com.bene20.vendas.api.dto.InformacoesItemPedidoDTO;
import br.com.bene20.vendas.api.dto.InformacoesPedidoDTO;
import br.com.bene20.vendas.api.dto.PedidoDTO;
import br.com.bene20.vendas.domain.entity.ItemPedido;
import br.com.bene20.vendas.domain.entity.Pedido;
import br.com.bene20.vendas.service.PedidoService;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
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
}
