package projeto_base_de_telas_e_login.adapter.out.persistence.Pedido.PDF.PdfTemplatePedido;

import projeto_base_de_telas_e_login.adapter.in.web.dto.Pedido.pdfDTO.ItemPedidoPdfDTO;
import projeto_base_de_telas_e_login.adapter.in.web.dto.Pedido.pdfDTO.PedidoPdfDTO;
import projeto_base_de_telas_e_login.domain.model.Pedido.Pedido;
import projeto_base_de_telas_e_login.domain.model.Preco.Preco;

import java.math.BigDecimal;
import java.util.List;

public class PedidoPdfMapper {

    public static PedidoPdfDTO from(Pedido pedido) {

        List<ItemPedidoPdfDTO> itens = pedido.getItens()
                .stream()
                .map(item -> {

                    var precoUnit = item.getProduto().getPreco();
                    var subtotal = precoUnit.multiplicar(item.getQuantidade());

                    return new ItemPedidoPdfDTO(
                            item.getProduto().getName(),
                            item.getProduto().getImagemUrl(),
                            item.getQuantidade(),
                            precoUnit.toString(),
                            subtotal.toString()
                    );
                })
                .toList();

        Preco total = new Preco(BigDecimal.ZERO);

        for (var item : pedido.getItens()) {
            total = total.somar(
                    item.getProduto().getPreco()
                            .multiplicar(item.getQuantidade())
            );
        }

        return new PedidoPdfDTO(
                pedido.getId(),
                pedido.getCriado().toString(),
                pedido.getCliente(),
                pedido.getTelefone(),
                pedido.getEndereco(),
                pedido.getBairro().toString(),
                pedido.getComplemento(),
                pedido.getFormaDePagamento().toString(),
                total.toString(),
                itens
        );
    }
}