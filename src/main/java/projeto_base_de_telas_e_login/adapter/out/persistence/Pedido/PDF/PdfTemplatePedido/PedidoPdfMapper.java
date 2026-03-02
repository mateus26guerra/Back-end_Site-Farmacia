package projeto_base_de_telas_e_login.adapter.out.persistence.Pedido.PDF.PdfTemplatePedido;

import projeto_base_de_telas_e_login.domain.model.Pedido.Pedido;

import java.util.List;

public class PedidoPdfMapper {

    public static PedidoPdfDTO from(Pedido pedido) {

        List<ItemPedidoPdfDTO> itens = pedido.getItens()
                .stream()
                .map(item -> new ItemPedidoPdfDTO(
                        item.getNomeProduto(),
                        item.getQuantidade(),
                        item.getPrecoUnitario().getValor(),
                        item.getSubtotal().getValor()
                ))
                .toList();

        return new PedidoPdfDTO(
                pedido.getId(),
                pedido.getNomeCliente(),
                pedido.getEmail(),
                pedido.getTelefone(),
                pedido.getEndereco(),
                pedido.getBairro(),
                pedido.getComplemento(),
                pedido.getObservacao(),
                pedido.getStatus().name(),
                pedido.getTipoEntrega().name(),
                pedido.getCriadoEm(),
                pedido.getTotalProdutos().getValor(),
                pedido.getValorFrete().getValor(),
                pedido.getTotalFinal().getValor(),
                pedido.getFreteGratis(),
                itens
        );
    }
}