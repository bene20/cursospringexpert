package br.com.bene20.vendas.api.controller;

import br.com.bene20.vendas.api.dto.CredenciaisDTO;
import br.com.bene20.vendas.api.dto.TokenDTO;
import br.com.bene20.vendas.domain.entity.Usuario;
import br.com.bene20.vendas.exception.SenhaInvalidaException;
import br.com.bene20.vendas.security.jwt.JWTService;
import br.com.bene20.vendas.service.impl.UsuarioServiceImpl;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
  
  private final UsuarioServiceImpl usuarioServiceImpl;
  private final PasswordEncoder passwordEncoder;
  private final JWTService jWTService;
  
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Usuario salvar(@RequestBody @Valid Usuario usuario){
    usuario.setId(null);
    usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
    
    return usuarioServiceImpl.salvar(usuario);
  }
  
  @GetMapping("{id}")
  public Usuario getById(@PathVariable("id") Integer id){
    return usuarioServiceImpl
            .getById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                               "Registro não encontrado"));
  }
  
  @PostMapping("auth")
  public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciaisDTO){
    try {
      Usuario usuario = Usuario
                          .builder()
                          .login(credenciaisDTO.getLogin())
                          .senha(credenciaisDTO.getSenha())
                          .build();
      UserDetails usuarioAutenticado = usuarioServiceImpl.autenticar(usuario);
      String token = jWTService.gerarToken(usuario);
      return new TokenDTO(usuario.getLogin(), token);
    } catch (UsernameNotFoundException | SenhaInvalidaException ex){
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }
  }
}
