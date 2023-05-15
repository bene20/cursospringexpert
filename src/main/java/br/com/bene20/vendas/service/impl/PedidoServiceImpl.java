package br.com.bene20.vendas.service.impl;

import br.com.bene20.vendas.exception.PedidoNaoEncontradoException;
import br.com.bene20.vendas.api.dto.ItemPedidoDTO;
import br.com.bene20.vendas.api.dto.PedidoDTO;
import br.com.bene20.vendas.domain.entity.Cliente;
import br.com.bene20.vendas.domain.entity.ItemPedido;
import br.com.bene20.vendas.domain.entity.Pedido;
import br.com.bene20.vendas.domain.entity.Produto;
import br.com.bene20.vendas.domain.enums.StatusPedido;
import br.com.bene20.vendas.domain.repository.ClienteRepository;
import br.com.bene20.vendas.domain.repository.ItemPedidoRepository;
import br.com.bene20.vendas.domain.repository.PedidoRepository;
import br.com.bene20.vendas.domain.repository.ProdutoRepository;
import br.com.bene20.vendas.exception.RegraNegocioException;
import br.com.bene20.vendas.service.PedidoService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor //O lombok implementa o construtor com todos os argumentos obrigatórios (os que têm final)
public class PedidoServiceImpl implements PedidoService{
    
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Cliente cliente = clienteRepository
                                .findById(dto.getCliente())
                                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido: "+dto.getCliente()));
        
        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);
        
        pedidoRepository.save(pedido);
        
        List<ItemPedido> itensPedido = converterItensPedido(pedido, dto.getItens());
        itemPedidoRepository.saveAll(itensPedido);
        pedido.setItens(itensPedido);
        
        return pedido;
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido status) {
        pedidoRepository
                .findById(id)
                .map(pedido -> {
                    pedido.setStatus(status);
                    pedidoRepository.save(pedido);
                    return Void.class;
                })
                .orElseThrow(() -> new PedidoNaoEncontradoException("Pedido "+id+" não encontrado."));
    }
    
    private List<ItemPedido> converterItensPedido(Pedido pedido,
                                                  List<ItemPedidoDTO> itensPedidoDTO){
        if((itensPedidoDTO == null) || (itensPedidoDTO.isEmpty())){
            throw new RegraNegocioException("Não é possível realizar um pedido sem ítens.");
        }
     
        return itensPedidoDTO
                .stream()
                .map( dto -> {
                    Produto produto = produtoRepository
                            .findById(dto.getProduto())
                            .orElseThrow(() -> new RegraNegocioException("Código de produto inválido: "+ dto.getProduto()));
                    
                    ItemPedido entidade = new ItemPedido();
                    entidade.setQuantidade(dto.getQuantidade());
                    entidade.setPedido(pedido);
                    entidade.setProduto(produto);
                    return entidade;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidoRepository.findByIdFetchItens(id);
    }

}
