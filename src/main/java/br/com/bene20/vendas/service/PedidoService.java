package br.com.bene20.vendas.service;

import br.com.bene20.vendas.api.dto.PedidoDTO;
import br.com.bene20.vendas.domain.entity.Pedido;
import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);
    
    Optional<Pedido> obterPedidoCompleto(Integer id);
}
