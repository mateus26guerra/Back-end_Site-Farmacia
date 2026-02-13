package projeto_base_de_telas_e_login.adapter.in.web.dto.Pedido;

import projeto_base_de_telas_e_login.domain.model.ItemPedido.ItemPedido;
import projeto_base_de_telas_e_login.domain.model.Pedido.Bairro;
import projeto_base_de_telas_e_login.domain.model.Pedido.FormaDePagamento;
import projeto_base_de_telas_e_login.domain.model.Pedido.Pedido;
import projeto_base_de_telas_e_login.domain.model.product.Product;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoAddDTO(
        String cliente,
        String telefone,
        String endereco,
        Bairro bairro,
        String complemento,
        List<ItemPedidoAddDTO> itens,
        FormaDePagamento formaDePagamento
) {

    /**
     * ⚠️ Atenção:
     * Aqui NÃO buscamos produto no banco.
     * Isso deve ser feito no USE CASE / SERVICE.
     */
    public Pedido toDomain(List<Product> produtosDoBanco) {

        if (itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("Pedido deve conter ao menos um item");
        }

        var itensPedido = itens.stream().map(dto -> {

            Product produto = produtosDoBanco.stream()
                    .filter(p -> p.getId().equals(dto.produtoId()))
                    .findFirst()
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "Produto com ID " + dto.produtoId() + " não encontrado"
                            )
                    );

            if (dto.quantidade() <= 0) {
                throw new IllegalArgumentException("Quantidade inválida para o produto " + produto.getName());
            }

            if (produto.getQuantidadeEmEstoque() < dto.quantidade()) {
                throw new IllegalArgumentException(
                        "Estoque insuficiente para o produto " + produto.getName()
                );
            }

            return new ItemPedido(
                    null,
                    produto,
                    dto.quantidade()
            );
        }).toList();

        return new Pedido(
                null,
                LocalDateTime.now(),
                cliente,
                telefone,
                endereco,
                bairro,
                complemento,
                itensPedido,
                formaDePagamento
        );
    }
}
