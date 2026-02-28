package projeto_base_de_telas_e_login.domain.model.estoque;

import projeto_base_de_telas_e_login.domain.model.Loja.Loja;
import projeto_base_de_telas_e_login.domain.model.Preco.Preco;
import projeto_base_de_telas_e_login.domain.model.product.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Estoque {

    private Long id;
    private Loja loja;
    private Product produto;
    private Integer quantidade;

    private Preco precoVenda;
    private BigDecimal percentualDesconto; // 🔥 NOVO

    private LocalDateTime atualizadoEm;

    public Estoque() {}

    public Estoque(
            Long id,
            Loja loja,
            Product produto,
            Integer quantidade,
            Preco precoVenda,
            BigDecimal percentualDesconto
    ) {
        this.id = id;
        this.loja = loja;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
        this.percentualDesconto = percentualDesconto != null ? percentualDesconto : BigDecimal.ZERO;
        this.atualizadoEm = LocalDateTime.now();
    }

    public void baixarEstoque(int quantidadeVendida) {
        if (quantidadeVendida > this.quantidade) {
            throw new IllegalArgumentException("Estoque insuficiente");
        }

        this.quantidade -= quantidadeVendida;
        this.atualizadoEm = LocalDateTime.now();
    }

    public Preco getPrecoComDesconto() {

        if (percentualDesconto.compareTo(BigDecimal.ZERO) <= 0) {
            return precoVenda;
        }

        BigDecimal desconto = precoVenda.getValor()
                .multiply(percentualDesconto)
                .divide(BigDecimal.valueOf(100));

        BigDecimal precoFinal = precoVenda.getValor().subtract(desconto);

        return new Preco(precoFinal);
    }

    public boolean temPromocao() {
        return percentualDesconto.compareTo(BigDecimal.ZERO) > 0;
    }

    public Long getId() { return id; }
    public Loja getLoja() { return loja; }
    public Product getProduto() { return produto; }
    public Integer getQuantidade() { return quantidade; }
    public Preco getPrecoVenda() { return precoVenda; }
    public BigDecimal getPercentualDesconto() { return percentualDesconto; }
    public LocalDateTime getAtualizadoEm() { return atualizadoEm; }
}