package br.com.bene20.vendas.domain.repositorio;

import br.com.bene20.vendas.domain.entity.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class Clientes {
    
    private static String INSERT = "insert into cliente(nome) values (?) ";
    private static String SELECT_ALL = "select * from cliente ";
    private static String UPDATE = "update cliente set nome = ? where id = ? ";
    private static String DELETE = "delete from cliente where id = ? ";
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public Cliente salvar(Cliente cliente){
        jdbcTemplate.update(INSERT, 
                                new Object[]{cliente.getNome()});
        return cliente;
    }
    
    public Cliente atualizar(Cliente cliente){
        jdbcTemplate.update(UPDATE, 
                                new Object[]{cliente.getNome(), 
                                             cliente.getId()});
        return cliente;
    }
    
    
    public void deletar(Cliente cliente){
        deletar(cliente.getId());
    }
    
    public void deletar(Integer id){
        jdbcTemplate.update(DELETE, new Object[]{id});
    }
    
    public List<Cliente> buscarPorNome(String nome){
        return jdbcTemplate.query(SELECT_ALL.concat("where nome like ?"),
                                      new Object[]{"%"+nome+"%"},
                                  obterClienteMapper());
    }
    
    public List<Cliente> obterTodos(){
        return jdbcTemplate.query(SELECT_ALL, obterClienteMapper());
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
