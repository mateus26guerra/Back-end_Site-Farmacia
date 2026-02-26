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
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String variacao;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "preco_id", nullable = false)
    private PrecoEntity preco;

    @Column(name = "image_url")
    private String imagemUrl;

    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaEntity categoria;


    public ProductEntity() {
        // JPA
    }

    // ✅ Domain → Entity (CORRETO)
    public ProductEntity(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.variacao = product.getVariacao();
        this.preco = new PrecoEntity(product.getPreco());
        this.imagemUrl = product.getImagemUrl();
        this.categoria = new CategoriaEntity(product.getCategoria());
    }

    // Entity → Domain
    public Product toDomain() {
        return new Product(
                this.id,
                this.name,
                this.variacao,
                this.preco.toDomain(),
                this.imagemUrl,
                this.categoria.toDomain()
        );
    }
    public void setId(Long id) {
        this.id = id;
    }

}
