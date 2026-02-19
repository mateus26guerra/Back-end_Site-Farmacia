package projeto_base_de_telas_e_login.domain.repository;

import projeto_base_de_telas_e_login.domain.model.product.Product;

import java.util.List;
import java.util.Optional;

import java.util.List;

public interface ProdutoPorta {

    void save(Product product);

    List<Product> findAll();

    List<Product> findByCategoriaNome(String nomeCategoria); // 🔥 ADICIONAR

    void deleteById(Long id);

    List<Product> findAllByIds(List<Long> ids); // 🔥 ADICIONAR

}
