package projeto_base_de_telas_e_login.domain.repository;

import projeto_base_de_telas_e_login.domain.model.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProdutoPorta {

    void save(Product product);

    List<Product> findAll();

    List<Product> findByCategoriaId(Long categoriaId);

    List<Product> findByCategoriaEmEstoque(Long categoriaId);

    List<Product> findAllByIds(List<Long> ids);


    void deleteById(Long id);
}
