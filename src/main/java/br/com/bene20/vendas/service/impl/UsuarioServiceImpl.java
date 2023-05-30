package br.com.bene20.vendas.service.impl;

import br.com.bene20.vendas.domain.entity.Usuario;
import br.com.bene20.vendas.domain.repository.UsuarioRepository;
import br.com.bene20.vendas.exception.SenhaInvalidaException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/***
 * Permite efetuar o carregamento de usuários a partir de uma base de dados
 * @author ebenezer.botelho
 */
@Service
public class UsuarioServiceImpl implements UserDetailsService {

  @Autowired
  private PasswordEncoder passwordEncoder; 
  
  @Autowired
  private UsuarioRepository usuarioRepository;
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario usuario = usuarioRepository
            .findByLogin(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));
    
    String roles[] = usuario.isAdmin() ? 
                       new String[]{"ADMIN", "USER"} :
                       new String[]{"USER"};
    
    return User
            .builder()
            .username(usuario.getLogin())
            .password(usuario.getSenha())
            .roles(roles)
            .build();
  }

  @Transactional
  public Usuario salvar(Usuario usuario) {
    return usuarioRepository.save(usuario);
  }

  public Optional<Usuario> getById(Integer id) {
    return usuarioRepository.findById(id);
  }
  
  public UserDetails autenticar(Usuario usuario){
    UserDetails userDetails = loadUserByUsername(usuario.getLogin());
    //No método abaixo, não inverter a ordem dos parâmetros. O primeiro dev ser a 
    //senha fornecida pelo usuário, a segunda é o hash da senha armazenada no banco de dados
    boolean senhaOK = passwordEncoder.matches(usuario.getSenha(), userDetails.getPassword());
    
    if(senhaOK){
      return userDetails;
    }
    throw new SenhaInvalidaException();
  }
  
}
