package br.com.bene20.vendas.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoDTO {
    
    private Integer produto;
    
    private Integer quantidade;
    
}
