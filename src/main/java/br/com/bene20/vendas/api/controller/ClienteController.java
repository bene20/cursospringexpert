package br.com.bene20.vendas.api.controller;

import br.com.bene20.vendas.domain.entity.Cliente;
import br.com.bene20.vendas.domain.repository.ClienteRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
@Api("Api Clientes")
public class ClienteController {
    
    private final ClienteRepository repository;

    @GetMapping("{id}")
    @ApiOperation("Obter detalhes de um cliente")
    @ApiResponses({
      @ApiResponse(code = 200, message = "Cliente encontrado"),
      @ApiResponse(code = 404, message = "Cliente não encontrado para o id informado")
    })
    public Cliente getById(@PathVariable("id") @ApiParam("ID do cliente") Integer id){
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                               "Registro não encontrado"));
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Salva um novo cliente")
    @ApiResponses({
      @ApiResponse(code = 201, message = "Cliente salvo com sucesso"),
      @ApiResponse(code = 400, message = "Erro de validação")
    })
    public Cliente save(@RequestBody @Valid Cliente registro){
        registro.setId(null);
        
        return repository.save(registro);
    }
    
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Integer id,
                       @RequestBody @Valid Cliente registro){
        repository
                .findById(id)
                .map(registroExistente -> {
                    registro.setId(registroExistente.getId());
                    repository.save(registro);
                    return registro;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                               "Registro não encontrado"));
    }
    
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id){
        repository
                .findById(id)
                .map(registro -> {
                    repository.delete(registro);
                    return Void.class;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                               "Registro não encontrado"));
    }
    
    @GetMapping
    public List<Cliente> find(Cliente filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        
        Example example = Example.of(filtro, matcher);
        
        return repository.findAll(example);
    }
}
