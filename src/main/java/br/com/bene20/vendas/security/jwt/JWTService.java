package br.com.bene20.vendas.security.jwt;

import br.com.bene20.vendas.VendasApplication;
import br.com.bene20.vendas.domain.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
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
    HashMap<String, Object> claims = new HashMap<>();
    claims.put("email", "user@gmail.com");
    claims.put("ultimoAcesso", "05/01/2019");
    
    return Jwts
            .builder()
            .setClaims(claims)
            .setSubject(usuario.getLogin())
            .setExpiration(getDataExpiracaoJWT())
            .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
            .compact();
  }
  
  public boolean isTokenValido(String token){
    try{
      Claims claims = obterClaims(token);
      Date dataExpiracao = claims.getExpiration();
      LocalDateTime dateTimeExpiracao = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
      return ! LocalDateTime.now().isAfter(dateTimeExpiracao);
    } catch (Exception e){
      return false;
    }
  }
  
  public String obterLoginUsuario(String token) throws ExpiredJwtException {
    return (String) obterClaims(token)
                      .getSubject();
  }
  
  private Claims obterClaims(String token) throws ExpiredJwtException{
    return Jwts
            .parser()
            .setSigningKey(chaveAssinatura)
            .parseClaimsJws(token)
            .getBody();
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
    System.out.println("Token válido? " + jWTService.isTokenValido(token));
    System.out.println("Usuário do token: " + jWTService.obterLoginUsuario(token));
  }
}
