package br.com.bene20.localizacao.service;

import br.com.bene20.localizacao.domain.repository.CidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CidadeService {
  
  private final CidadeRepository cidadeRepository;

  public void listarCidades(){
    cidadeRepository
            .findAll()
            .forEach(System.out::println);
  }
  
  public void listarCidadesPorNome(){
    cidadeRepository.findByNome("Porto Velho").forEach(System.out::println);
    cidadeRepository.findByNomeLike("%al%").forEach(System.out::println);
    cidadeRepository.findByNomeLikeInsensitive("%port%").forEach(System.out::println);
    cidadeRepository.findByNomeStartingWith("Rio").forEach(System.out::println);
    cidadeRepository.findByNomeEndingWith("Paulo").forEach(System.out::println);
    cidadeRepository.findByNomeContaining("Porto").forEach(System.out::println);
  }
  
  public void listarCidadesPorHabitantes(){
//    cidadeRepository.findByHabitantes(111111L).forEach(System.out::println);
//    cidadeRepository.findByHabitantesLessThan(236L).forEach(System.out::println);
//    cidadeRepository.findByHabitantesLessThanEqual(235L).forEach(System.out::println);
//    cidadeRepository.findByHabitantesGreaterThan(2000L).forEach(System.out::println);
    cidadeRepository.findByHabitantesLessThanAndNomeLike(555555L, "%a%").forEach(System.out::println);
  }  
}
