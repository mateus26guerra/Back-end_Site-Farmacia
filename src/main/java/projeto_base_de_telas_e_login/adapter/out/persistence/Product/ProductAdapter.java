package projeto_base_de_telas_e_login.adapter.out.persistence.Product;

import org.springframework.stereotype.Component;
import projeto_base_de_telas_e_login.adapter.out.persistence.categoria.CategoriaEntity;
import projeto_base_de_telas_e_login.adapter.out.persistence.categoria.CategoriaRepository;
import projeto_base_de_telas_e_login.domain.model.product.Product;
import projeto_base_de_telas_e_login.domain.repository.ProdutoPorta;

import java.util.List;
import java.util.Optional;

@Component
public class ProductAdapter implements ProdutoPorta {

    private final ProductRepository repository;
    private final CategoriaRepository categoriaRepository;

    public ProductAdapter(
            ProductRepository repository,
            CategoriaRepository categoriaRepository
    ) {
        this.repository = repository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void save(Product product) {

        if (product.getCategoria() == null || product.getCategoria().getId() == null) {
            throw new IllegalArgumentException("Categoria é obrigatória");
        }

        Integer categoriaId = product.getCategoria().getId();

        CategoriaEntity categoriaEntity = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        ProductEntity entity = new ProductEntity(product, categoriaEntity);

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
    public List<Product> findByCategoriaId(Integer categoriaId) {
        return repository.findByCategoria_Id(categoriaId)
                .stream()
                .map(ProductEntity::toDomain)
                .toList();
    }

    @Override
    public List<Product> findByCategoriaEmEstoque(Integer categoriaId) {
        return repository.findByCategoria_IdAndTemEmEstoqueTrue(categoriaId)
                .stream()
                .map(ProductEntity::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
