package projeto_base_de_telas_e_login.domain.model.product;

import projeto_base_de_telas_e_login.domain.model.Preco.Preco;
import projeto_base_de_telas_e_login.domain.model.categoria.Categoria;

public class Product {

    private Long id;
    private String name;

    // Ex: P, M, G, 500mg, Azul
    private String variacao;

    private String imagemUrl;
    private Categoria categoria;
    private Preco preco;

    // Controle real de estoque
    private Integer quantidadeEmEstoque;

    public Product() {
    }

    public Product(
            Long id,
            String name,
            String variacao,
            Preco preco,
            String imagemUrl,
            Categoria categoria,
            Integer quantidadeEmEstoque
    ) {
        this.id = id;
        this.name = name;
        this.variacao = variacao;
        this.preco = preco;
        this.imagemUrl = imagemUrl;
        this.categoria = categoria;
        setQuantidadeEmEstoque(quantidadeEmEstoque);
    }

    public Product(
            String name,
            String variacao,
            Preco preco,
            String imagemUrl,
            Categoria categoria,
            Integer quantidadeEmEstoque
    ) {
        this(null, name, variacao, preco, imagemUrl, categoria, quantidadeEmEstoque);
    }

    // ───── Regras de domínio ─────

    public boolean temEmEstoque() {
        return quantidadeEmEstoque != null && quantidadeEmEstoque > 0;
    }

    public void baixarEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade inválida");
        }
        if (quantidadeEmEstoque < quantidade) {
            throw new IllegalArgumentException("Estoque insuficiente");
        }
        this.quantidadeEmEstoque -= quantidade;
    }



    // ───── Getters e Setters ─────

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVariacao() {
        return variacao;
    }

    public void setVariacao(String variacao) {
        this.variacao = variacao;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Preco getPreco() {
        return preco;
    }

    public void setPreco(Preco preco) {
        this.preco = preco;
    }

    public Integer getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(Integer quantidadeEmEstoque) {
        if (quantidadeEmEstoque == null || quantidadeEmEstoque < 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo");
        }
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }
    public boolean isEmEstoque() {
        return quantidadeEmEstoque != null && quantidadeEmEstoque > 0;
    }
}
