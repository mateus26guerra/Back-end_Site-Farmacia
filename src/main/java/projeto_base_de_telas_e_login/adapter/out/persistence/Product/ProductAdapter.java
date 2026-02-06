package projeto_base_de_telas_e_login.adapter.out.persistence.Product;

import org.springframework.stereotype.Component;
import projeto_base_de_telas_e_login.domain.model.product.Product;
import projeto_base_de_telas_e_login.domain.repository.porta.ProdutoPorta;

import java.util.List;
@Component
public class ProductAdapter implements ProdutoPorta {

    private final ProductRepository repository;

    public ProductAdapter(ProductRepository repository) {
        this.repository = repository;
    }

    // Domain → Entity → DB
    @Override
    public void save(Product product) {
        ProductEntity entity = new ProductEntity(product);
        repository.save(entity);
    }

    // DB → Entity → Domain
    @Override
    public List<Product> findAll() {
        return repository.findAll()
                .stream()
                .map(ProductEntity::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
