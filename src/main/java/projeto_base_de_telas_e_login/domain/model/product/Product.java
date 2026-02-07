package projeto_base_de_telas_e_login.domain.model.product;

import projeto_base_de_telas_e_login.domain.model.Preco.Preco;
import projeto_base_de_telas_e_login.domain.model.categoria.Categoria;

import java.math.BigDecimal;

public class Product {

    private Integer id;
    private String name;
    private String imagemUrl;
    private Categoria categoria;
    private Preco preco;
    private Boolean temEmEstoque;

    public Product() {
    }

    public Product(Integer id, String name, Preco preco, String imagemUrl, Categoria categoria,Boolean temEmEstoque) {
        this.id = id;
        this.name = name;
        this.preco = preco;
        this.imagemUrl = imagemUrl;
        this.categoria = categoria;
        this.temEmEstoque = temEmEstoque;
    }

    public Product(String name,Preco preco, String imagemUrl, Categoria categoria,Boolean temEmEstoque) {
        this(null, name, preco, imagemUrl, categoria,temEmEstoque);
    }

    // getters e setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Preco getPreco() { return preco; }
    public void setPreco(Preco preco) { this.preco = preco; }

    public String getImagemUrl() { return imagemUrl; }
    public void setImagemUrl(String imagemUrl) { this.imagemUrl = imagemUrl; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public Boolean getTemEmEstoque() { return temEmEstoque; }
    public void setTemEmEstoque(Boolean temEmEstoque) { this.temEmEstoque = temEmEstoque; }

}
