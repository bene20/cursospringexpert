package br.com.bene20.vendas.api.controller;

import br.com.bene20.vendas.domain.entity.Cliente;
import br.com.bene20.vendas.domain.repository.ClienteRepository;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {
    
    private ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Integer idCliente){
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);
        
        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    @ResponseBody
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente){
        cliente.setId(null);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        
        return ResponseEntity.ok(clienteSalvo);
    }
    
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity update(@PathVariable("id") Integer idCliente,
                                          @RequestBody Cliente cliente){
        return clienteRepository
                 .findById(idCliente)
                 .map( cliExistente -> {
                     cliente.setId(cliExistente.getId());
                     clienteRepository.save(cliente);
                     return ResponseEntity.noContent().build();
                 })
                .orElseGet( () -> ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable("id") Integer idCliente){
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);
        
        if(cliente.isPresent()){
            clienteRepository.delete(cliente.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }        
    }
}
