package br.com.bene20.vendas.domain.repositorio;

import br.com.bene20.vendas.domain.entity.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class Clientes {
    
    @Autowired
    private EntityManager em;
    
    @Transactional
    public Cliente salvar(Cliente cliente){
        em.persist(cliente);
        return cliente;
    }
    
    @Transactional
    public Cliente atualizar(Cliente cliente){
        em.merge(cliente);
        return cliente;
    }
    
    @Transactional
    public void deletar(Cliente cliente){
        if(!em.contains(cliente)){
            cliente = em.merge(cliente);
        }
        em.remove(cliente);
    }
    
    @Transactional
    public void deletar(Integer id){
        Cliente cliente = em.find(Cliente.class, id);
        deletar(cliente);
    }
    
    @Transactional
    public List<Cliente> buscarPorNome(String nome){
        return em
          .createQuery("select c from Cliente c where c.nome like :nome", 
                       Cliente.class)
          .setParameter("nome", "%"+nome+"%")
          .getResultList();
    }
    
    public List<Cliente> obterTodos(){
        return em.createQuery("select c from Cliente c", 
                              Cliente.class)
                 .getResultList();
    }

    private RowMapper<Cliente> obterClienteMapper() {
        return new RowMapper<Cliente> () {
            @Override
            public Cliente mapRow(ResultSet rs, int i) throws SQLException {
                return new Cliente(rs.getInt("id"), rs.getString("nome"));
            }
        };
    }
}
