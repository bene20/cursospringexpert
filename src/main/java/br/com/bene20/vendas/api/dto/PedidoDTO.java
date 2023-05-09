package br.com.bene20.vendas.api.dto;

import java.math.BigDecimal;
import java.util.List;

public class PedidoDTO {
    
    private Integer cliente;
    
    private BigDecimal total;
    
    private List<ItemPedidoDTO> itens;
    
}
