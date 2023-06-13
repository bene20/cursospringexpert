package br.com.bene20.vendas.domain.entity;

import br.com.bene20.vendas.domain.enums.StatusPedido;
import br.com.bene20.vendas.validation.NotEmptyList;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @NotNull(message = "{campo.codigo-cliente.obrigatorio}")
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    
    @Column(name = "data_pedido", nullable = false)
    private LocalDate dataPedido;
    
    @NotNull(message = "{campo.total-pedido.obrigatorio}")
    @Column(name = "total", scale = 2, precision = 20, nullable = false)
    private BigDecimal total;

    @NotEmptyList(message = "{campo.itens-pedido.obrigatorio}")
    @ToString.Exclude
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusPedido status;
}
