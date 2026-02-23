package projeto_base_de_telas_e_login.adapter.in.web.dto.Pedido;


import projeto_base_de_telas_e_login.domain.model.Pedido.Enum.Bairro;
import projeto_base_de_telas_e_login.domain.model.Pedido.Enum.FormaDePagamento;
import projeto_base_de_telas_e_login.domain.model.Pedido.Pedido;

import java.util.List;

import java.time.LocalDateTime;

public record ListaDePedidoDTO(
        Long id,
        LocalDateTime criado,
        String cliente,
        String telefone,
        String endereco,
        Bairro bairro,
        String complemento,
        FormaDePagamento formaDePagamento,
        List<ItemPedidoDTO> itens
) {

    public static ListaDePedidoDTO fromDomain(Pedido pedido) {

        List<ItemPedidoDTO> itensDto = pedido.getItens()
                .stream()
                .map(ItemPedidoDTO::fromDomain)
                .toList();

        return new ListaDePedidoDTO(
                pedido.getId(),
                pedido.getCriado(),
                pedido.getCliente(),
                pedido.getTelefone(),
                pedido.getEndereco(),
                pedido.getBairro(),
                pedido.getComplemento(),
                pedido.getFormaDePagamento(),
                itensDto
        );
    }
}
