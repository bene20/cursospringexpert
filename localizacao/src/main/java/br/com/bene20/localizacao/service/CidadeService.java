package br.com.bene20.localizacao.service;

import br.com.bene20.localizacao.domain.entity.Cidade;
import br.com.bene20.localizacao.domain.repository.CidadeRepository;
import br.com.bene20.localizacao.domain.repository.projection.CidadeProjection;
import br.com.bene20.localizacao.domain.repository.specs.CidadeSpecs;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class CidadeService {
  
  private final CidadeRepository cidadeRepository;

  public List<Cidade> listarCidades(){
    return cidadeRepository.findAll();
  }
  
  public void listarCidadesPorNome(){
//    cidadeRepository.findByNome("Porto Velho").forEach(System.out::println);
//    cidadeRepository.findByNomeLike("%al%").forEach(System.out::println);
//    cidadeRepository.findByNomeLike("%a%", Sort.by("habitantes")).forEach(System.out::println);
    cidadeRepository.findByNomeLike("%al%", PageRequest.of(1, 2)).forEach(System.out::println);
//    cidadeRepository.findByNomeLikeInsensitive("%port%").forEach(System.out::println);
//    cidadeRepository.findByNomeStartingWith("Rio").forEach(System.out::println);
//    cidadeRepository.findByNomeEndingWith("Paulo").forEach(System.out::println);
//    cidadeRepository.findByNomeContaining("Porto").forEach(System.out::println);
  }
  
  public void listarCidadesPorHabitantes(){
//    cidadeRepository.findByHabitantes(111111L).forEach(System.out::println);
//    cidadeRepository.findByHabitantesLessThan(236L).forEach(System.out::println);
//    cidadeRepository.findByHabitantesLessThanEqual(235L).forEach(System.out::println);
//    cidadeRepository.findByHabitantesGreaterThan(2000L).forEach(System.out::println);
    cidadeRepository.findByHabitantesLessThanAndNomeLike(555555L, "%a%").forEach(System.out::println);
  }

  public List<Cidade> filtroDinamico(Cidade cidade){
    ExampleMatcher matcher = ExampleMatcher.matching()
              .withIgnoreCase()
              .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    Example<Cidade> example = Example.of(cidade, matcher);
    return cidadeRepository.findAll(example);
  }
  
  public List<Cidade> listarCidadesByNomeSpec(String nome){
    return cidadeRepository.findAll(
            CidadeSpecs.nomeEqual(nome)
                  .and(CidadeSpecs.habitantesGreaterThan(100L))
    );
  }
  
  public List<Cidade> listarCidadesByNomeiLike(String nome){
    return cidadeRepository.findAll(
            CidadeSpecs.nomeiLike(nome)
    );
  }
  
  public List<Cidade> listarCidadesByNomeNativo(String nome){
    return cidadeRepository.findByNomeNativo(nome);
  }
  
  public List<CidadeProjection> listarCidadesByNomeNativoProjection(String nome){
    return cidadeRepository
            .findByNomeNativoProjection(nome);
//    return cidadeRepository
//            .findByNomeNativoProjection(nome)
//            .stream().map(c -> new Cidade(c.getIdCidade(), c.getNomeCidade(), null))
//            .collect(Collectors.toList());
  }
  
  public List<Cidade> listarCidadesFiltroDinamicoSpec(Cidade filtro){
    //Obtendo um spec do tipo 'select * from cidade where 1=1'
    Specification<Cidade> specs = Specification.where((root, query, cb) -> cb.conjunction());
    
    if(filtro.getId() != null){
      specs = specs.and(CidadeSpecs.IdEqual(filtro.getId()));
    }
    
    if(StringUtils.hasText(filtro.getNome())){
      specs = specs.and(CidadeSpecs.nomeiLike(filtro.getNome()));
    }
    
    if(filtro.getHabitantes() != null){
      specs = specs.and(CidadeSpecs.habitantesGreaterThan(filtro.getHabitantes()));
    }
    
    return cidadeRepository.findAll(specs);
  }
}
