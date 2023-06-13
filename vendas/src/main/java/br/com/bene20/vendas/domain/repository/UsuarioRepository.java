package br.com.bene20.vendas.domain.repository;

import br.com.bene20.vendas.domain.entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
  
  Optional<Usuario> findByLogin(String login);
}
