package br.com.bene20.vendas.config;

import javafx.beans.binding.Bindings;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
  
  /***
   * Método responsável por configurar a autenticação (como o usuário verificará a senha)
   * @param http
   * @throws Exception 
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .inMemoryAuthentication()
      .passwordEncoder(passwordEncoder())
      .withUser("bene20")
      .password(passwordEncoder().encode("bene20"))
      .roles("USER", "ADMIN");
  }
  
  /***
   * Método responsável por controlar as autorizações (verificar se o usuário autenticado tem permissão para fazer a ação requerida)
   * @param http
   * @throws Exception 
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .csrf().disable()
      .authorizeRequests()
        .antMatchers("/api/cliente/**").hasAnyRole("USER", "ADMIN")
        .antMatchers("/api/produto/**").hasRole("ADMIN")
        .antMatchers("/api/pedido/**").hasAnyRole("USER", "ADMIN")
      .and()
        .httpBasic();
  }

  
}
