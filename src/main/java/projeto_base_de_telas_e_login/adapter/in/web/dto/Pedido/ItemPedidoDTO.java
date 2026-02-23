package projeto_base_de_telas_e_login.adapter.in.web.dto.Pedido;

import projeto_base_de_telas_e_login.domain.model.ItemPedido.ItemPedido;

public record ItemPedidoDTO(
        Long produtoId,
        String nomeProduto,
        String variacao,
        String imagemUrl,
        String categoria,
        String preco,
        Integer quantidade
) {

    public static ItemPedidoDTO fromDomain(ItemPedido item) {

        var produto = item.getProduto();

        return new ItemPedidoDTO(
                produto.getId(),
                produto.getName(),
                produto.getVariacao(),
                produto.getImagemUrl(),
                produto.getCategoria().getNome_categoria(),
                produto.getPreco().getValorFinal().toString(),
                item.getQuantidade()
        );
    }
}