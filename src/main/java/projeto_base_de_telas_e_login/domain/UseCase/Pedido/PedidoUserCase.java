package projeto_base_de_telas_e_login.domain.UseCase.Pedido;

import org.springframework.stereotype.Service;
import projeto_base_de_telas_e_login.adapter.in.web.dto.Pedido.ItemPedidoAddDTO;
import projeto_base_de_telas_e_login.adapter.in.web.dto.Pedido.PedidoAddDTO;
import projeto_base_de_telas_e_login.domain.model.ItemPedido.ItemPedido;
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

        // 🔐 valida se todos existem
        if (produtosBanco.size() != ids.size()) {
            throw new IllegalArgumentException("Um ou mais produtos não existem");
        }

        var itens = dto.itens().stream().map(item -> {

            Product produto = produtosBanco.stream()
                    .filter(p -> p.getId().equals(item.produtoId()))
                    .findFirst()
                    .orElseThrow();

            produto.baixarEstoque(item.quantidade());

            return new ItemPedido(
                    null,
                    produto,
                    item.quantidade()
            );
        }).toList();

        Pedido pedido = new Pedido(
                null,
                LocalDateTime.now(),
                dto.cliente(),
                dto.telefone(),
                dto.endereco(),
                dto.bairro(),
                dto.complemento(),
                itens,
                dto.formaDePagamento()
        );

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

}
