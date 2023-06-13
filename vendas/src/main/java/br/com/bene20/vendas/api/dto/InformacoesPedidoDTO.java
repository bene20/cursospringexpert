package br.com.bene20.vendas.api.dto;

import br.com.bene20.vendas.domain.entity.Pedido;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InformacoesPedidoDTO {
    
    private Integer codigo;
    
    private String cpf;
    
    private String nomeCliente;
    
    private BigDecimal total;
    
    private String dataPedido;
    
    private String status;
    
    @ToString.Exclude
    private List<InformacoesItemPedidoDTO> itens;
    
    public static InformacoesPedidoDTO fromEntityPedido(Pedido pedido) {
        return InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .itens( InformacoesItemPedidoDTO.fromItemPedidoEntityList(pedido.getItens()))
                .build();
    }    
}
