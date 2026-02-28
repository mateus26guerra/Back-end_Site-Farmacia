package projeto_base_de_telas_e_login.domain.model.estoque;

import projeto_base_de_telas_e_login.domain.model.Loja.Loja;
import projeto_base_de_telas_e_login.domain.model.Preco.Preco;
import projeto_base_de_telas_e_login.domain.model.product.Product;

import java.time.LocalDateTime;

public class Estoque {

    private Long id;
    private Loja loja;
    private Product produto;
    private Integer quantidade;
    private Preco precoVenda;
    private LocalDateTime atualizadoEm;

    public Estoque() {}

    public Estoque(
            Long id,
            Loja loja,
            Product produto,
            Integer quantidade,
            Preco precoVenda
    ) {
        this.id = id;
        this.loja = loja;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
        this.atualizadoEm = LocalDateTime.now();
    }

    public void baixarEstoque(int quantidadeVendida) {
        if (quantidadeVendida > this.quantidade) {
            throw new IllegalArgumentException("Estoque insuficiente");
        }

        this.quantidade -= quantidadeVendida;
        this.atualizadoEm = LocalDateTime.now();
    }

    public Long getId() { return id; }

    public Loja getLoja() { return loja; }

    public Product getProduto() { return produto; }

    public Integer getQuantidade() { return quantidade; }

    public Preco getPrecoVenda() { return precoVenda; }

    public LocalDateTime getAtualizadoEm() { return atualizadoEm; }
}