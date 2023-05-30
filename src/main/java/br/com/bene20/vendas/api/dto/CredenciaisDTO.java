package br.com.bene20.vendas.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CredenciaisDTO {
  private String login;
  private String senha;
}
