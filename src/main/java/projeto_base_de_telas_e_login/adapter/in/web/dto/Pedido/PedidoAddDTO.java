package projeto_base_de_telas_e_login.adapter.in.web.dto.Pedido;

import projeto_base_de_telas_e_login.domain.model.ItemPedido.ItemPedido;
import projeto_base_de_telas_e_login.domain.model.Pedido.*;
import projeto_base_de_telas_e_login.domain.model.Pedido.Enum.Bairro;
import projeto_base_de_telas_e_login.domain.model.Pedido.Enum.FormaDePagamento;
import projeto_base_de_telas_e_login.domain.model.Pedido.Enum.TipoEntrega;
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
        FormaDePagamento formaDePagamento,
        TipoEntrega tipoEntrega,
        String observacao,
        String cep
) {

    /**
     * DTO → Domain (snapshot do pedido)
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
                cep,
                itensPedido,
                formaDePagamento,
                tipoEntrega,
                observacao);
    }
}