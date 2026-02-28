package projeto_base_de_telas_e_login.adapter.out.persistence.Pedido.PDF.PdfTemplatePedido;

import projeto_base_de_telas_e_login.adapter.in.web.dto.Pedido.pdfDTO.ItemPedidoPdfDTO;
import projeto_base_de_telas_e_login.adapter.in.web.dto.Pedido.pdfDTO.PedidoPdfDTO;
import projeto_base_de_telas_e_login.domain.model.Pedido.Pedido;

import java.util.List;

public class PedidoPdfMapper {

    public static PedidoPdfDTO from(Pedido pedido) {

        List<ItemPedidoPdfDTO> itens = pedido.getItens()
                .stream()
                .map(item ->
                        new ItemPedidoPdfDTO(
                                item.getNomeProduto(),
                                null, // imagem
                                item.getQuantidade(),
                                item.getPrecoUnitario().toString(),
                                item.getSubtotal().toString()
                        )
                )
                .toList();

        return new PedidoPdfDTO(
                pedido.getId(),                             // 1
                pedido.getCriadoEm().toString(),             // 2
                pedido.getNomeCliente(),                     // 3
                pedido.getTelefone(),                        // 4
                pedido.getEndereco(),                        // 5
                pedido.getBairro(),                          // 6
                "",                                          // 7 -> complemento (não existe no Pedido)
                pedido.getStatus().toString(),               // 8
                pedido.getTotalFinal().toString(),           // 9
                itens                                        // 10
        );
    }
}