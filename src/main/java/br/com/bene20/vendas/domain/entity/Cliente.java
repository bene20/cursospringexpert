package br.com.bene20.vendas.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
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
@Table(name = "cliente")
public class Cliente implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "nome", length = 100)
    private String nome;
    
    @Column(name = "cpf", length = 11)
    private String cpf;

    @ToString.Exclude
    @JsonIgnore //Anotação para não incluir esta proprieade no Json gerado para a API
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;
 }
