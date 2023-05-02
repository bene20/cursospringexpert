package br.com.bene20.vendas.domain.repositorio;

import br.com.bene20.vendas.domain.entity.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Clientes extends JpaRepository<Cliente, Integer>{

    public List<Cliente> findByNomeLike(String nome);

    public boolean existsByNome(String pedro);
    
}
