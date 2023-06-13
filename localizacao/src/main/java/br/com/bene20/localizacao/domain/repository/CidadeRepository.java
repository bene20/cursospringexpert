package br.com.bene20.localizacao.domain.repository;

import br.com.bene20.localizacao.domain.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{
  
}
