package br.com.bene20.localizacao.domain.repository;

import br.com.bene20.localizacao.domain.entity.Cidade;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface CidadeRepository extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade>{
  
  List<Cidade> findByNome(String nome);
  
  List<Cidade> findByNomeLike(String nome);
  
  // Método ordenado
  List<Cidade> findByNomeLike(String nome, Sort sort);
  
  // Método paginado
  Page<Cidade> findByNomeLike(String nome, Pageable pageable);
  
  @Query("select c from Cidade c where upper(c.nome) like upper(?1)")
  List<Cidade> findByNomeLikeInsensitive(String nome);
  
  List<Cidade> findByNomeStartingWith(String nome);
  
  List<Cidade> findByNomeEndingWith(String nome);
  
  List<Cidade> findByNomeContaining(String nome);
  
  List<Cidade> findByHabitantes(Long qtdHabitantes);
  List<Cidade> findByHabitantesLessThan(Long qtdHabitantes);
  List<Cidade> findByHabitantesLessThanEqual(Long qtdHabitantes);
  List<Cidade> findByHabitantesGreaterThan(Long qtdHabitantes);
  
  List<Cidade> findByHabitantesLessThanAndNomeLike(Long qtdHabitantes, String nome);
}
