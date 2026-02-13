package projeto_base_de_telas_e_login.domain.model.ItemPedido;

import projeto_base_de_telas_e_login.domain.model.product.Product;

import java.math.BigDecimal;

public class ItemPedido {


    private Long id;
    private Product produto;
    private Integer quantidade;

    public ItemPedido() {}

    public ItemPedido(Long id, Product produto, Integer quantidade) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto é obrigatório");
        }
        if (quantidade == null || quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade inválida");
        }
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
    }
    public BigDecimal getSubtotal() {
        return produto.getPreco()
                .getValor()
                .multiply(BigDecimal.valueOf(quantidade));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduto() {
        return produto;
    }

    public void setProduto(Product produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
