package br.com.bene20.vendas.domain.repository;

import br.com.bene20.vendas.domain.entity.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

    public List<Cliente> findByNomeLike(String nome);
    
    //Método encontrarPorNome abaixo é equivalente ao findByNomeLike acima
    @Query(value = "select c from Cliente c where c.nome like :nome")
    public List<Cliente> encontrarPorNome(@Param("nome") String nome);

    @Query(value = "delete from Cliente c where c.nome like :nome")
    @Modifying
    public void deleteByNome(@Param("nome") String nome);

    public boolean existsByNome(String nome);
    
    @Query("SELECT c FROM Cliente c LEFT JOIN FETCH c.pedidos where c.id = :id")
    public Cliente findClienteFetchPedidos(@Param("id") Integer Id);
    
}
