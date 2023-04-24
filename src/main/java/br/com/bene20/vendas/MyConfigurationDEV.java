package br.com.bene20.vendas;

import br.com.bene20.vendas.annotations.Development;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Development
public class MyConfigurationDEV {
    
    @Bean
    public CommandLineRunner executar(){
        return args -> {
            System.out.println("CARREGADO ARQUIVO PROPERTIES DE DEVELOPMENT");
        };
    }
}
