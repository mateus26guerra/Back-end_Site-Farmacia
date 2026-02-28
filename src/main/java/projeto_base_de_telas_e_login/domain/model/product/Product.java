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

    // Adicionamos o preço do produto
    private Preco precoVenda;

    public Product() {}

    public Product(
            Long id,
            String name,
            String variacao,
            String imagemUrl,
            Categoria categoria,
            Preco precoVenda
    ) {
        this.id = id;
        this.name = name;
        this.variacao = variacao;
        this.imagemUrl = imagemUrl;
        this.categoria = categoria;
        this.precoVenda = precoVenda;
    }

    public Product(
            String name,
            String variacao,
            String imagemUrl,
            Categoria categoria,
            Preco precoVenda
    ) {
        this(null, name, variacao, imagemUrl, categoria, precoVenda);
    }

    // ───── Getters e Setters ─────

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getVariacao() { return variacao; }
    public String getImagemUrl() { return imagemUrl; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public Preco getPrecoVenda() { return precoVenda; }
    public void setPrecoVenda(Preco precoVenda) { this.precoVenda = precoVenda; }
}