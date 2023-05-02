package br.com.bene20.vendas;

import br.com.bene20.vendas.domain.entity.Cliente;
import br.com.bene20.vendas.domain.repositorio.Clientes;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return args -> {
            clientes.save(new Cliente("Pedro"));
            clientes.save(new Cliente("Ana"));
            clientes.save(new Cliente("Caio"));
            
            System.out.println("0 - Existe um Pietro na base? " + clientes.existsByNome("Pietro"));
            
            System.out.println("1 - Relação de todos os nomes:");
            clientes.findAll().forEach(System.out::println );
            
            System.out.println("2 - Alterando todos os nomes:");
            clientes.findAll().forEach(c -> {
                c.setNome(c.getNome() + " atualizado");
                clientes.save(c);
            });
            clientes.findAll().forEach(System.out::println);
            
            System.out.println("3 - Pequisando cliente %na%:");
            clientes.findByNomeLike("%na%").forEach(System.out::println );

            System.out.println("4 - Excluindo os clientes:");
            clientes.findAll().forEach(c -> {
                clientes.delete(c);
            });

            System.out.println("5 - Listagem final dos clientes:");
            clientes.findAll().forEach(System.out::println);;
        };
    }
    
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
