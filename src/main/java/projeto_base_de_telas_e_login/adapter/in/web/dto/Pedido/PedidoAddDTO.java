package projeto_base_de_telas_e_login.adapter.in.web.dto.Pedido;

import projeto_base_de_telas_e_login.domain.model.Loja.Loja;
import projeto_base_de_telas_e_login.domain.model.Pedido.Enum.TipoEntrega;
import projeto_base_de_telas_e_login.domain.model.Pedido.Pedido;
import projeto_base_de_telas_e_login.domain.model.product.Product;
import projeto_base_de_telas_e_login.domain.model.ItemPedido.ItemPedido;

import java.util.List;

public record PedidoAddDTO(

        Long lojaId,
        String nomeCliente,
        String email,
        String telefone,
        String endereco,
        String bairro,
        String complemento,
        String observacao,
        TipoEntrega tipoEntrega,
        List<ItemPedidoAddDTO> itens

) {

    public Pedido toDomain(List<Product> produtosBanco) {

        Loja lojaStub = new Loja(lojaId, null, null);

        return new Pedido(
                lojaStub,
                nomeCliente,
                email,
                telefone,
                endereco,
                bairro,
                complemento,
                observacao,
                tipoEntrega,
                itens.stream()
                        .map(itemDto -> {
                            Product produto = produtosBanco.stream()
                                    .filter(p -> p.getId().equals(itemDto.produtoId()))
                                    .findFirst()
                                    .orElseThrow();

                            return new ItemPedido(
                                    produto.getName(),
                                    produto.getPrecoVenda(),
                                    itemDto.quantidade()
                            );
                        })
                        .toList()
        );
    }
}