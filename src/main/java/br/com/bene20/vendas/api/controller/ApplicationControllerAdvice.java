package br.com.bene20.vendas.api.controller;

import br.com.bene20.vendas.api.ApiErrors;
import br.com.bene20.vendas.exception.PedidoNaoEncontradoException;
import br.com.bene20.vendas.exception.RegraNegocioException;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {
    
    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex){
        return new ApiErrors(ex.getMessage());
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePedidoNaoEncontradoException(PedidoNaoEncontradoException ex){
        return new ApiErrors(ex.getMessage());
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        return new ApiErrors(ex
                                .getBindingResult()
                                .getAllErrors()
                                .stream()
                                .map(erro -> erro.getDefaultMessage())
                                .collect(Collectors.toList())
        );
    }
}
