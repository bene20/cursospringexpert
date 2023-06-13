package br.com.bene20.localizacao;

import br.com.bene20.localizacao.domain.entity.Cidade;
import br.com.bene20.localizacao.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

  @Autowired
  private CidadeRepository cidadeRepository;
  
  @Override
  public void run(String... args) throws Exception {
    System.out.println("Inicializado!");
    listarCidadesPorHabitantes();
  }
  
  void listarCidades(){
    cidadeRepository
            .findAll()
            .forEach(System.out::println);
  }
  
  void listarCidadesPorNome(){
    cidadeRepository.findByNome("Porto Velho").forEach(System.out::println);
    cidadeRepository.findByNomeLike("%al%").forEach(System.out::println);
    cidadeRepository.findByNomeLikeInsensitive("%port%").forEach(System.out::println);
    cidadeRepository.findByNomeStartingWith("Rio").forEach(System.out::println);
    cidadeRepository.findByNomeEndingWith("Paulo").forEach(System.out::println);
    cidadeRepository.findByNomeContaining("Porto").forEach(System.out::println);
  }
  
  void listarCidadesPorHabitantes(){
//    cidadeRepository.findByHabitantes(111111L).forEach(System.out::println);
//    cidadeRepository.findByHabitantesLessThan(236L).forEach(System.out::println);
//    cidadeRepository.findByHabitantesLessThanEqual(235L).forEach(System.out::println);
//    cidadeRepository.findByHabitantesGreaterThan(2000L).forEach(System.out::println);
    cidadeRepository.findByHabitantesLessThanAndNomeLike(555555L, "%a%").forEach(System.out::println);
  }

  public static void main(String[] args) {
    SpringApplication.run(LocalizacaoApplication.class, args);
  }

}
