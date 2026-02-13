package projeto_base_de_telas_e_login.domain.UseCase.Pedido;

import org.springframework.stereotype.Service;
import projeto_base_de_telas_e_login.adapter.in.web.dto.Pedido.ItemPedidoAddDTO;
import projeto_base_de_telas_e_login.adapter.in.web.dto.Pedido.PedidoAddDTO;
import projeto_base_de_telas_e_login.domain.model.ItemPedido.ItemPedido;
import projeto_base_de_telas_e_login.domain.model.Pedido.Pedido;
import projeto_base_de_telas_e_login.domain.model.product.Product;
import projeto_base_de_telas_e_login.domain.repository.PedidoPorta;
import projeto_base_de_telas_e_login.domain.repository.ProdutoPorta;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoUserCase {

    private final PedidoPorta pedidoPorta;
    private final ProdutoPorta produtoPorta;

    public PedidoUserCase(PedidoPorta pedidoPorta, ProdutoPorta produtoPorta) {
        this.pedidoPorta = pedidoPorta;
        this.produtoPorta = produtoPorta;
    }

    public void criarPedido(PedidoAddDTO dto) {

        var ids = dto.itens().stream()
                .map(ItemPedidoAddDTO::produtoId)
                .toList();

        List<Product> todos = produtoPorta.findAllByIds(List.of(1L, 2L, 3L, 4L));
        System.out.println("Produtos encontrados manualmente: " + todos.size());

        List<Product> produtosBanco = produtoPorta.findAllByIds(ids);

        for (Long id : ids) {
            boolean existe = produtosBanco.stream()
                    .anyMatch(p -> p.getId().equals(id));

            if (!existe) {
                throw new IllegalArgumentException("Produto inexistente no banco: " + id);
            }
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


    public List<Pedido> listarTodos() {
        return pedidoPorta.findAll();
    }

}
