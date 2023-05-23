package br.com.bene20.vendas.service;

import br.com.bene20.vendas.VendasApplication;
import br.com.bene20.vendas.domain.entity.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class JWTService {
  
  @Value("${security.jwt.expiracao}")
  private String expiracao;
  
  @Value("${security.jwt.chave-assinatura}")
  private String chaveAssinatura;
  
  public String gerarToken(Usuario usuario){
    return Jwts
            .builder()
            .setSubject(usuario.getLogin())
            .setExpiration(getDataExpiracaoJWT())
            .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
            .compact();
  }

  private Date getDataExpiracaoJWT() throws NumberFormatException {
    long expString = Long.valueOf(expiracao);
    LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
    Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
    Date data = Date.from(instant);
    return data;
  }
  
  /***
   * Método main simplesmente para testar a geração do Token acima
   * @param args 
   */
  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(VendasApplication.class, args);
    JWTService jWTService = context.getBean(JWTService.class);
    
    Usuario usuario = Usuario.builder().login("bene20").senha("123").build();
    String token = jWTService.gerarToken(usuario);
    System.out.println("Token: " + token);
  }
}
