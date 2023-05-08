package br.com.bene20.vendas;

import br.com.bene20.vendas.domain.entity.Cliente;
import br.com.bene20.vendas.domain.entity.Pedido;
import br.com.bene20.vendas.domain.repository.Clientes;
import br.com.bene20.vendas.domain.repository.Pedidos;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(
            @Autowired Clientes clientes,
            @Autowired Pedidos pedidos
            ){
        return args -> {
            teste1(clientes);
            teste2(clientes, pedidos);
        };
    }

    private void teste1(Clientes clientes) {
        Cliente beto = new Cliente("Beto");
        clientes.save(beto);
        
        Cliente ana = new Cliente("Ana");
        clientes.save(ana);
        
        Cliente caio = new Cliente("Caio");
        clientes.save(caio);
        
        System.out.println("0 - Existe um Pietro na base? " + clientes.existsByNome("Pietro"));
        
        System.out.println("1 - Relação de todos os nomes:");
        clientes.findAll().forEach(System.out::println );
        
        System.out.println("2 - Alterando todos os nomes:");
        clientes.findAll().forEach(c -> {
            c.setNome(c.getNome() + " atualizado");
            clientes.save(c);
        });
        clientes.findAll().forEach(System.out::println);
        
        System.out.println("3a - Pequisando cliente %na%:");
        clientes.findByNomeLike("%na%").forEach(System.out::println );
        
        System.out.println("3b - Pequisando cliente %na%:");
        clientes.encontrarPorNome("%na%").forEach(System.out::println );
        
        System.out.println("4 - Excluindo os clientes:");
        clientes.findAll().forEach(c -> {
            clientes.delete(c);
        });
        
        System.out.println("5 - Listagem final dos clientes:");
        clientes.findAll().forEach(System.out::println);
    }

    private void teste2(Clientes clientes, Pedidos pedidos) {
        Cliente pedro = new Cliente("Pedro");
        clientes.save(pedro);
        
        Pedido p1 = new Pedido();
        p1.setCliente(pedro);
        p1.setDataPedido(LocalDate.now());
        p1.setTotal(BigDecimal.valueOf(100.00));
        pedidos.save(p1);
        
        Pedido p2 = new Pedido();
        p2.setCliente(pedro);
        p2.setDataPedido(LocalDate.now());
        p2.setTotal(BigDecimal.valueOf(120.00));
        pedidos.save(p2);
        
        Cliente pedrofull = clientes.findClienteFetchPedidos(pedro.getId());
        System.out.println("Cliente: " + pedrofull.getNome());
        System.out.println("Qtd pedidos: " + pedrofull.getPedidos().size());
        System.out.println("Pedidos: ");
        pedrofull.getPedidos().forEach(System.out::println);
        
        System.out.println("Relação de pedidos de pedro: ");
        pedidos.findByCliente(pedro).forEach(System.out::println);
    }
    
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
