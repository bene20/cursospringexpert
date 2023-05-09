package br.com.bene20.vendas.service.impl;

import br.com.bene20.vendas.domain.repository.PedidoRepository;
import br.com.bene20.vendas.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService{
    
    private PedidoRepository repository;

    public PedidoServiceImpl(PedidoRepository repository) {
        this.repository = repository;
    }

    
}
