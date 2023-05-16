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
    
    @NotNull(message = "{campo.codigo-cliente.obrigatorio}")
    private Integer cliente;
    
    @NotNull(message = "{campo.total-pedido.obrigatorio}")
    private BigDecimal total;
    
    @ToString.Exclude
    @NotEmptyList(message = "{campo.itens-pedido.obrigatorio}")
    private List<ItemPedidoDTO> itens;
}
