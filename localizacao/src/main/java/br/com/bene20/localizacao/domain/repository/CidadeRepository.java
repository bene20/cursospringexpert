package br.com.bene20.localizacao.domain.repository;

import br.com.bene20.localizacao.domain.entity.Cidade;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{
  
  List<Cidade> findByNome(String nome);
  
  List<Cidade> findByNomeLike(String nome);
  
  @Query("select c from Cidade c where upper(c.nome) like upper(?1)")
  List<Cidade> findByNomeLikeInsensitive(String nome);
  
  List<Cidade> findByNomeStartingWith(String nome);
  
  List<Cidade> findByNomeEndingWith(String nome);
  
  List<Cidade> findByNomeContaining(String nome);
  
  List<Cidade> findByHabitantes(Long qtdHabitantes);
}
