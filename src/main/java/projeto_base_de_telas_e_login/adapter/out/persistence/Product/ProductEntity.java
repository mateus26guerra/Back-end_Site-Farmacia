package projeto_base_de_telas_e_login.adapter.out.persistence.Product;

import jakarta.persistence.*;
import projeto_base_de_telas_e_login.adapter.out.persistence.Preco.PrecoEntity;
import projeto_base_de_telas_e_login.adapter.out.persistence.categoria.CategoriaEntity;
import projeto_base_de_telas_e_login.domain.model.product.Product;

@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "preco_id", nullable = false)
    private PrecoEntity preco;

    @Column(name = "image_url")
    private String imagemUrl;

    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaEntity categoria;

    @Column(name = "tem_em_estoque", nullable = false)
    private Boolean temEmEstoque = true;

    protected ProductEntity() {
        // JPA
    }

    public ProductEntity(Product product, CategoriaEntity categoria) {
        this.id = product.getId();
        this.name = product.getName();
        this.preco = new PrecoEntity(product.getPreco());
        this.imagemUrl = product.getImagemUrl();
        this.categoria = categoria;
        this.temEmEstoque = product.getTemEmEstoque() != null
                ? product.getTemEmEstoque()
                : true;
    }

    public Product toDomain() {
        Product product = new Product();
        product.setId(this.id);
        product.setName(this.name);
        product.setPreco(this.preco.toDomain());
        product.setImagemUrl(this.imagemUrl);
        product.setCategoria(this.categoria.toDomain());
        product.setTemEmEstoque(this.temEmEstoque);
        return product;
    }
}
