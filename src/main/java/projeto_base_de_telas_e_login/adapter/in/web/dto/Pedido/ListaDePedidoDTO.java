package projeto_base_de_telas_e_login.adapter.in.web.dto.Pedido;

import projeto_base_de_telas_e_login.domain.model.Pedido.Enum.FormaDePagamento;
import projeto_base_de_telas_e_login.domain.model.Pedido.Enum.StatusDoPedido;
import projeto_base_de_telas_e_login.domain.model.Pedido.Enum.TipoEntrega;
import projeto_base_de_telas_e_login.domain.model.Pedido.Pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ListaDePedidoDTO(

        Long id,
        LocalDateTime criadoEm,
        String nomeCliente,
        String telefone,
        String endereco,
        String bairro,
        FormaDePagamento formaDePagamento,
        StatusDoPedido status,
        TipoEntrega tipoEntrega,
        List<ItemPedidoDTO> itens,
        BigDecimal totalProdutos,
        BigDecimal valorFrete,
        BigDecimal totalFinal,
        boolean freteGratis

) {
    public static ListaDePedidoDTO fromDomain(Pedido pedido) {

        List<ItemPedidoDTO> itensDto = pedido.getItens()
                .stream()
                .map(ItemPedidoDTO::fromDomain)
                .toList();

        return new ListaDePedidoDTO(
                pedido.getId(),
                pedido.getCriadoEm(),
                pedido.getNomeCliente(),
                pedido.getTelefone(),
                pedido.getEndereco(),
                pedido.getBairro(),
                null, // se quiser depois adicionamos formaPagamento no model
                pedido.getStatus(),
                pedido.getTipoEntrega(),
                itensDto,
                pedido.getTotalProdutos().getValor(),   // ⚠️ Lembre de converter Preco → BigDecimal
                pedido.getValorFrete().getValor(),      // ⚠️ mesmo aqui
                pedido.getTotalFinal().getValor(),      // ⚠️ mesmo aqui
                pedido.getFreteGratis()                 // ✅ substitui isFreteGratis()
        );
    }
}