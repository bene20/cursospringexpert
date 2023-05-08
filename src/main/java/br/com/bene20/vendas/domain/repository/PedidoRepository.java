package br.com.bene20.vendas.domain.repository;

import br.com.bene20.vendas.domain.entity.Cliente;
import br.com.bene20.vendas.domain.entity.Pedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
 
    List<Pedido> findByCliente(Cliente cliente);
}
