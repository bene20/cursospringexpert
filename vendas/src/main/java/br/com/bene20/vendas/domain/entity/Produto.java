package br.com.bene20.vendas.domain.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produto")
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    @Column(name = "descricao", length = 100, nullable = false)
    private String descricao;
    
    @NotNull(message = "{campo.preco.obrigatorio}")
    @Column(name = "preco_unitario", scale = 2, precision = 20, nullable = false)
    private BigDecimal preco;
}
