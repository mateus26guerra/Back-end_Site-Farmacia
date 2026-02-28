package projeto_base_de_telas_e_login.adapter.out.persistence.Product;

import jakarta.persistence.*;
import projeto_base_de_telas_e_login.adapter.out.persistence.categoria.CategoriaEntity;
import projeto_base_de_telas_e_login.domain.model.Preco.Preco;
import projeto_base_de_telas_e_login.domain.model.product.Product;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String variacao;

    @Column(name = "image_url")
    private String imagemUrl;

    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id")
    private CategoriaEntity categoria;

    // Adicionando campo para o preço
    @Column(name = "preco_venda", precision = 10, scale = 2)
    private BigDecimal precoVenda;

    protected ProductEntity() {}

    public ProductEntity(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.variacao = product.getVariacao();
        this.imagemUrl = product.getImagemUrl();
        this.categoria = new CategoriaEntity(product.getCategoria());
        this.precoVenda = product.getPrecoVenda() != null ? product.getPrecoVenda().getValor() : null;
    }

    public Product toDomain() {
        return new Product(
                this.id,
                this.name,
                this.variacao,
                this.imagemUrl,
                this.categoria.toDomain(),
                this.precoVenda != null ? new Preco(this.precoVenda) : null
        );
    }

    // ───── Getters ─────
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getVariacao() { return variacao; }
    public String getImagemUrl() { return imagemUrl; }
    public CategoriaEntity getCategoria() { return categoria; }
    public BigDecimal getPrecoVenda() { return precoVenda; }
}