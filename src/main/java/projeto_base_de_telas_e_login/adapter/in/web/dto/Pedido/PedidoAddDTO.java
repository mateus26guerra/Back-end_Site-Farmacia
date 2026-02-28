//package projeto_base_de_telas_e_login.adapter.in.web.dto.Pedido;
//
//import projeto_base_de_telas_e_login.domain.model.ItemPedido.ItemPedido;
//import projeto_base_de_telas_e_login.domain.model.Pedido.Enum.TipoEntrega;
//import projeto_base_de_telas_e_login.domain.model.Pedido.Pedido;
//import projeto_base_de_telas_e_login.domain.model.product.Product;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public record PedidoAddDTO(
//        Long lojaId,
//        String nomeCliente,
//        String telefone,
//        String endereco,
//        String bairro,
//        TipoEntrega tipoEntrega,
//        List<ItemPedidoAddDTO> itens
//) {
//
//    public Pedido toDomain(List<Product> produtosBanco) {
//
//        List<ItemPedido> itensPedido = itens.stream()
//                .map(itemDto -> {
//
//                    Product produto = produtosBanco.stream()
//                            .filter(p -> p.getId().equals(itemDto.produtoId()))
//                            .findFirst()
//                            .orElseThrow(() ->
//                                    new RuntimeException("Produto não encontrado: " + itemDto.produtoId())
//                            );
//
//                    return new ItemPedido(
//                            produto.getName(),
//                            produto.getPrecoVenda(),
//                            itemDto.quantidade()
//                    );
//
//                })
//                .collect(Collectors.toList());
//
//        // Criando um stub de Loja a partir do ID
//        projeto_base_de_telas_e_login.domain.model.Loja.Loja lojaStub = new projeto_base_de_telas_e_login.domain.model.Loja.Loja();
//        lojaStub.setId(lojaId);
//
//        return new Pedido(
//                lojaStub,
//                nomeCliente,
//                telefone,
//                endereco,
//                bairro,
//                tipoEntrega,
//                itensPedido
//        );
//    }
//}