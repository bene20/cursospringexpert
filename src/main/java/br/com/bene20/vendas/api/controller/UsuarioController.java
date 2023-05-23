package br.com.bene20.vendas.api.controller;

import br.com.bene20.vendas.domain.entity.Usuario;
import br.com.bene20.vendas.service.impl.UsuarioServiceImpl;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
}
