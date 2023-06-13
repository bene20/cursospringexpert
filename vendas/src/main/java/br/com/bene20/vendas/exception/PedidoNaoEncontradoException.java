package br.com.bene20.vendas.exception;

public class PedidoNaoEncontradoException extends RuntimeException {

    public PedidoNaoEncontradoException() {
        super("Pedido não encontrado");
    }

    public PedidoNaoEncontradoException(String string) {
        super(string);
    }
    
}
