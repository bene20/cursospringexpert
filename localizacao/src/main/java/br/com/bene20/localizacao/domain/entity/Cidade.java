package br.com.bene20.localizacao.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_cidade")
@Data @NoArgsConstructor @AllArgsConstructor
public class Cidade {
  
  @Id
  @Column(name = "id_cidade")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Column(name = "ds_nome", length = 50, nullable = false)
  private String nome;
  
  @Column(name = "nu_qtdhabitantes")
  private Long habitantes;
}
