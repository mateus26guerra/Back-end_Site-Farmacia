package projeto_base_de_telas_e_login.domain.UseCase.Produto;

import org.springframework.stereotype.Service;
import projeto_base_de_telas_e_login.domain.model.product.Product;
import projeto_base_de_telas_e_login.domain.repository.porta.ProdutoPorta;

import java.util.Collection;
import java.util.List;

@Service
public class ProdutoUseCase {

    private final ProdutoPorta produtoPorta;

    public ProdutoUseCase(ProdutoPorta produtoPorta) {
        this.produtoPorta = produtoPorta;
    }

    public void save(Product product) {
        produtoPorta.save(product);
    }

    public List<Product> findAll() {
        return produtoPorta.findAll();
    }

    public void deleteById(Integer id) {
        produtoPorta.deleteById(id);
    }
}
