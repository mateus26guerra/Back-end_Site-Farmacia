package projeto_base_de_telas_e_login.adapter.out.persistence.Product;

import org.springframework.stereotype.Component;
import projeto_base_de_telas_e_login.adapter.out.persistence.categoria.CategoriaEntity;
import projeto_base_de_telas_e_login.adapter.out.persistence.categoria.CategoriaRepository;
import projeto_base_de_telas_e_login.domain.model.product.Product;
import projeto_base_de_telas_e_login.domain.repository.ProdutoPorta;

import java.util.List;



@Component
public class ProductAdapter implements ProdutoPorta {

    private final ProductRepository repository;

    public ProductAdapter(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Product product) {

        ProductEntity entity = new ProductEntity(product);
        repository.save(entity);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll()
                .stream()
                .map(ProductEntity::toDomain)
                .toList();
    }

    @Override
    public List<Product> findByCategoriaNome(String nomeCategoria) {
        return repository.findByCategoria_NomeCategoria(nomeCategoria)
                .stream()
                .map(ProductEntity::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Product> findAllByIds(List<Long> ids) {

        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("Lista de IDs não pode ser vazia");
        }

        return repository.findAllById(ids)
                .stream()
                .map(ProductEntity::toDomain)
                .toList();
    }
}


