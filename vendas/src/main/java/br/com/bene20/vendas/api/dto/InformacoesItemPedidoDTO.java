package br.com.bene20.vendas.api.dto;

import br.com.bene20.vendas.domain.entity.ItemPedido;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InformacoesItemPedidoDTO {
    
    private String descricaoProduto;
    
    private BigDecimal precoUnitario;
    
    private Integer quantidade;
    
    public static List<InformacoesItemPedidoDTO> fromItemPedidoEntityList(List<ItemPedido> itensPedido){
        if(CollectionUtils.isEmpty(itensPedido)){
            return Collections.emptyList();
        }
        
        return itensPedido
                .stream()
                .map(item -> fromItemPedidoEntity(item))
                .collect(Collectors.toList());
    }  
    
    public static InformacoesItemPedidoDTO fromItemPedidoEntity(ItemPedido item){
        return InformacoesItemPedidoDTO
                        .builder()
                        .descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build();
    }    
}
