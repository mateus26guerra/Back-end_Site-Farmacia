package projeto_base_de_telas_e_login.domain.UseCase.Pedido;

import org.springframework.stereotype.Service;
import projeto_base_de_telas_e_login.adapter.in.web.dto.Pedido.ItemPedidoAddDTO;
import projeto_base_de_telas_e_login.adapter.in.web.dto.Pedido.PedidoAddDTO;
import projeto_base_de_telas_e_login.domain.model.ItemPedido.ItemPedido;
import projeto_base_de_telas_e_login.domain.model.Pedido.Enum.StatusDoPedido;
import projeto_base_de_telas_e_login.domain.model.Pedido.Pedido;
import projeto_base_de_telas_e_login.domain.model.product.Product;
import projeto_base_de_telas_e_login.domain.repository.GeradorPdfPort;
import projeto_base_de_telas_e_login.domain.repository.PedidoPorta;
import projeto_base_de_telas_e_login.domain.repository.ProdutoPorta;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoUserCase {

    private final PedidoPorta pedidoPorta;
    private final ProdutoPorta produtoPorta;
    private final GeradorPdfPort geradorPdfPort;

    public PedidoUserCase(PedidoPorta pedidoPorta, ProdutoPorta produtoPorta,GeradorPdfPort geradorPdfPort) {
        this.pedidoPorta = pedidoPorta;
        this.produtoPorta = produtoPorta;
        this.geradorPdfPort = geradorPdfPort;

    }

    public void criarPedido(PedidoAddDTO dto) {

        var ids = dto.itens().stream()
                .map(ItemPedidoAddDTO::produtoId)
                .toList();

        List<Product> produtosBanco = produtoPorta.findAllByIds(ids);

        if (produtosBanco.size() != ids.size()) {
            throw new IllegalArgumentException("Um ou mais produtos não existem");
        }

        Pedido pedido = dto.toDomain(produtosBanco);

        pedido.getItens().forEach(item -> {
            Product produto = item.getProduto();
            produto.baixarEstoque(item.getQuantidade());
        });

        pedido.calcularTotais();

        pedidoPorta.save(pedido);
    }

    public List<Pedido> listarTodosSemFiltro() {
        return pedidoPorta.findAll();
    }

    public List<Pedido> listarTodos() {

        LocalDate hoje = LocalDate.now();

        LocalDateTime inicioDoDia = hoje.atStartOfDay();
        LocalDateTime fimDoDia = hoje.atTime(23, 59, 59);

        return pedidoPorta.findByCriadoBetweenOrderByCriadoDesc(
                inicioDoDia,
                fimDoDia
        );
    }

    public byte[] imprimirPDF(Long id){

        Pedido pedido = pedidoPorta.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        return geradorPdfPort.gerarPdfPedido(pedido);
    }
    public void atualizarStatusPedido(Long id, StatusDoPedido status) {
        pedidoPorta.atualizarStatus(id, status);
    }
}
