package projeto_base_de_telas_e_login.domain.repository.porta;

import projeto_base_de_telas_e_login.domain.model.product.Product;

import java.util.List;

public interface ProdutoPorta {

    void save(Product product);
    List<Product> findAll();
    void deleteById(Integer id);

}
