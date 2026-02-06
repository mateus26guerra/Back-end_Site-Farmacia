package projeto_base_de_telas_e_login.adapter.out.persistence.Product;

import jakarta.persistence.*;
import projeto_base_de_telas_e_login.domain.model.product.Product;
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Double price;

    @Column(name = "image_url")
    private String imagemUrl;

    public ProductEntity() {}

    // Domain → Entity
    public ProductEntity(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.imagemUrl = product.getImagemUrl();
    }

    // Entity → Domain
    public Product toDomain() {
        Product product = new Product();
        product.setId(this.id);
        product.setName(this.name);
        product.setPrice(this.price);
        product.setImagemUrl(this.imagemUrl);
        return product;
    }
}
