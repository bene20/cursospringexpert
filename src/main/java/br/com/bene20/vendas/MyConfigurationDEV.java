package br.com.bene20.vendas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("development") //Este Configuration só será ativado quando a propriedade 'spring.profiles.active' for igual a 'development'
public class MyConfigurationDEV {
    
    @Bean
    public CommandLineRunner executar(){
        return args -> {
            System.out.println("CARREGADO ARQUIVO PROPERTIES DE DEVELOPMENT");
        };
    }
}
