package br.com.bene20.vendas.domain.repository;

import br.com.bene20.vendas.domain.entity.Cliente;
import br.com.bene20.vendas.domain.entity.Pedido;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
 
    List<Pedido> findByCliente(Cliente cliente);
 
    @Query(" SELECT p FROM Pedido p LEFT JOIN FETCH p.itens WHERE p.id = :id")
    Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
}
