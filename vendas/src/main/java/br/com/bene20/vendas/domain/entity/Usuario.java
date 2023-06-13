package br.com.bene20.vendas.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Usuario {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  @NotEmpty(message = "{campo.login.obrigatorio}")
  @Column(nullable = false)
  private String login;
  
  @NotEmpty(message = "{campo.senha.obrigatorio}")
  @Column(nullable = false)
  private String senha;
  
  @Column(nullable = false)
  private boolean admin;
}
