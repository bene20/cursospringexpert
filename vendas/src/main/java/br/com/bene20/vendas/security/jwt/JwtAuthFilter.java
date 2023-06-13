package br.com.bene20.vendas.security.jwt;

import br.com.bene20.vendas.service.impl.UsuarioServiceImpl;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthFilter extends OncePerRequestFilter{

  private JWTService jWTService;
  
  private UsuarioServiceImpl usuarioServiceImpl;

  public JwtAuthFilter(JWTService jWTService, UsuarioServiceImpl usuarioServiceImpl) {
    this.jWTService = jWTService;
    this.usuarioServiceImpl = usuarioServiceImpl;
  }
  
  @Override
  protected void doFilterInternal(HttpServletRequest hsr, 
                                  HttpServletResponse hsr1, 
                                  FilterChain fc) throws ServletException, IOException {
    String authorization = hsr.getHeader("Authorization");
    
    if((authorization != null) && authorization.startsWith("Bearer")){
      String token = authorization.split(" ")[1];
      
      if(jWTService.isTokenValido(token)){
        String loginUsuario = jWTService.obterLoginUsuario(token);
        UserDetails usuario = usuarioServiceImpl.loadUserByUsername(loginUsuario);
        UsernamePasswordAuthenticationToken user = 
                new UsernamePasswordAuthenticationToken(usuario, 
                                                       null, 
                                                       usuario.getAuthorities());
        user.setDetails(new WebAuthenticationDetailsSource()
                              .buildDetails(hsr));
        SecurityContextHolder.getContext().setAuthentication(user);
      }
    }
    
    fc.doFilter(hsr, hsr1);
  }
  
}
