package projeto_base_de_telas_e_login.adapter.in.web.dto.Pedido;

import projeto_base_de_telas_e_login.domain.model.Pedido.Enum.Bairro;
import projeto_base_de_telas_e_login.domain.model.Pedido.Enum.FormaDePagamento;
import projeto_base_de_telas_e_login.domain.model.Pedido.Enum.StatusDoPedido;
import projeto_base_de_telas_e_login.domain.model.Pedido.Enum.TipoEntrega;
import projeto_base_de_telas_e_login.domain.model.Pedido.Pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ListaDePedidoDTO(
        Long id,
        LocalDateTime criado,
        String cliente,
        String telefone,
        String endereco,
        Bairro bairro,
        String complemento,
        String cep,
        FormaDePagamento formaDePagamento,
        StatusDoPedido statusDoPedido,
        TipoEntrega tipoEntrega,
        String observacao,
        List<ItemPedidoDTO> itens,
        BigDecimal totalProdutos,
        BigDecimal valorFrete,
        BigDecimal totalComFrete,
        boolean freteGratis
) {

    public static ListaDePedidoDTO fromDomain(Pedido pedido) {

        pedido.calcularTotais(); // garante que os totais estejam atualizados

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
                pedido.getCep(),
                pedido.getFormaDePagamento(),
                pedido.getStatusDoPedido(),
                pedido.getTipoEntrega(),
                pedido.getObservacao(),
                itensDto,
                pedido.getTotalProdutos(),
                pedido.getValorFrete(),
                pedido.getTotalComFrete(),
                pedido.isFreteGratis()
        );
    }
}