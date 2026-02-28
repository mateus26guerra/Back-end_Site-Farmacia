//package projeto_base_de_telas_e_login.adapter.out.persistence.ItemPedido;
//
//import jakarta.persistence.*;
//import projeto_base_de_telas_e_login.adapter.out.persistence.Pedido.PedidoEntity;
//import projeto_base_de_telas_e_login.domain.model.ItemPedido.ItemPedido;
//import projeto_base_de_telas_e_login.domain.model.Preco.Preco;
//
//import java.math.BigDecimal;
//
//@Entity
//@Table(name = "pedido_item")
//public class ItemPedidoEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String nomeProduto;
//
//    @Column(precision = 10, scale = 2)
//    private BigDecimal precoUnitario;
//
//    private Integer quantidade;
//
//    @Column(precision = 10, scale = 2)
//    private BigDecimal subtotal;
//
//    @ManyToOne(optional = false)
//    @JoinColumn(name = "pedido_id")
//    private PedidoEntity pedido;
//
//    protected ItemPedidoEntity() {}
//
//    // DOMAIN -> ENTITY
//    public ItemPedidoEntity(ItemPedido item, PedidoEntity pedido) {
//        this.nomeProduto = item.getNomeProduto();
//        this.precoUnitario = item.getPrecoUnitario().getValor(); // Preco -> BigDecimal
//        this.quantidade = item.getQuantidade();
//        this.subtotal = item.getSubtotal().getValor(); // Preco -> BigDecimal
//        this.pedido = pedido;
//    }
//
//    // ENTITY -> DOMAIN
//    public ItemPedido toDomain() {
//        return new ItemPedido(
//                this.nomeProduto,
//                new Preco(this.precoUnitario), // BigDecimal -> Preco
//                this.quantidade
//        );
//    }
//
//    // GETTERS e SETTERS
//    public Long getId() { return id; }
//    public String getNomeProduto() { return nomeProduto; }
//    public BigDecimal getPrecoUnitario() { return precoUnitario; }
//    public Integer getQuantidade() { return quantidade; }
//    public BigDecimal getSubtotal() { return subtotal; }
//    public PedidoEntity getPedido() { return pedido; }
//
//    public void setId(Long id) { this.id = id; }
//    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }
//    public void setPrecoUnitario(BigDecimal precoUnitario) { this.precoUnitario = precoUnitario; }
//    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
//    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
//    public void setPedido(PedidoEntity pedido) { this.pedido = pedido; }
//}