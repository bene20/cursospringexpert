package br.com.bene20.vendas.service;

import br.com.bene20.vendas.model.Cliente;
import br.com.bene20.vendas.repository.ClientesRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {
    
    private ClientesRepository clientesRepository;

    public ClientesService(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    public void salvarCliente(Cliente cliente){
        validarCliente(cliente);
    }
    
    public void validarCliente(Cliente cliente){
        //Aplica validações
    }
}
