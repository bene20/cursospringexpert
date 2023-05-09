package br.com.bene20.vendas.api.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    
    private Integer cliente;
    
    private BigDecimal total;
    
    @ToString.Exclude
    private List<ItemPedidoDTO> itens;
}
