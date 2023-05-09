package br.com.bene20.vendas.domain.entity;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "produto")
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "descricao", length = 100)
    private String descricao;
    
    @Column(name = "preco_unitario", scale = 2, precision = 20)
    private BigDecimal preco;

    @ToString.Exclude
    @OneToMany(mappedBy = "produto")
    private List<ItemPedido> itens;
}
