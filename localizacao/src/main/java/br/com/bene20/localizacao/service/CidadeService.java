package br.com.bene20.localizacao.service;

import br.com.bene20.localizacao.domain.entity.Cidade;
import br.com.bene20.localizacao.domain.repository.CidadeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CidadeService {
  
  private final CidadeRepository cidadeRepository;

  public void listarCidades(){
    cidadeRepository.findAll().forEach(System.out::println);
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
}
