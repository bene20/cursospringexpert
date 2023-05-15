package br.com.bene20.vendas.api.dto;

import br.com.bene20.vendas.validation.NotEmptyList;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    
    @NotNull(message = "O campo 'cliente' do pedido é obrigatório.")
    private Integer cliente;
    
    @NotNull(message = "O campo 'total' do pedido é obrigatório.")
    private BigDecimal total;
    
    @ToString.Exclude
    @NotEmptyList(message = "A lista de itens do pedido não pode estar vazia.")
    private List<ItemPedidoDTO> itens;
}
