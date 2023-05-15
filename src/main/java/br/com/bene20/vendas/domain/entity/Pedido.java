package br.com.bene20.vendas.domain.entity;

import br.com.bene20.vendas.domain.enums.StatusPedido;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    
    @Column(name = "data_pedido")
    private LocalDate dataPedido;
    
    @Column(name = "total", scale = 2, precision = 20)
    private BigDecimal total;

    @ToString.Exclude
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedido status;
}
