package br.com.bene20.vendas.domain.repository;

import br.com.bene20.vendas.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer>{
    
}
