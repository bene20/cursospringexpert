package br.com.bene20.vendas.api.dto;

/**
 * {
 *   "cliente": 1,
 *   "total": 100,
 *   "itens": [
 *     {
 *       "produto": 1,
 *       "quantidade": 10
 *     }
 *   ]
 * }
 */
public class ItemPedidoDTO {
    
    private Integer produto;
    
    private Integer quantidade;
    
}
