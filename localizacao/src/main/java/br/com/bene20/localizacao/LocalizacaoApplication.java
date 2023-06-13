package br.com.bene20.localizacao;

import br.com.bene20.localizacao.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

  @Autowired
  private CidadeService cidadeService;
  
  @Override
  public void run(String... args) throws Exception {
    System.out.println("Inicializado!");
    cidadeService.listarCidadesPorNome();
  }
  
  public static void main(String[] args) {
    SpringApplication.run(LocalizacaoApplication.class, args);
  }

}
