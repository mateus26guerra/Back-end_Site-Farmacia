package projeto_base_de_telas_e_login.adapter.out.persistence.ItemPedido;

import jakarta.persistence.*;
import projeto_base_de_telas_e_login.adapter.out.persistence.Pedido.PedidoEntity;
import projeto_base_de_telas_e_login.adapter.out.persistence.Product.ProductEntity;
import projeto_base_de_telas_e_login.domain.model.ItemPedido.ItemPedido;
import projeto_base_de_telas_e_login.domain.model.product.Product;
@Entity
@Table(name = "pedido_item")
public class ItemPedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedido;

    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id")
    private ProductEntity produto;

    @Column(nullable = false)
    private Integer quantidade;

    public ItemPedidoEntity() {}

    public ItemPedidoEntity(ItemPedido item, PedidoEntity pedido) {
        this.id = item.getId();

        this.produto = new ProductEntity();
        this.produto.setId(item.getProduto().getId()); // só referencia o ID

        this.quantidade = item.getQuantidade();
        this.pedido = pedido;
    }


    public ItemPedido toDomain() {
        return new ItemPedido(
                this.id,
                this.produto.toDomain(),
                this.quantidade
        );
    }
}

