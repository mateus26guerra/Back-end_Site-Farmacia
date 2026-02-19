package projeto_base_de_telas_e_login.domain.UseCase.Produto;

import org.springframework.stereotype.Service;
import projeto_base_de_telas_e_login.domain.model.categoria.Categoria;
import projeto_base_de_telas_e_login.domain.model.product.Product;
import projeto_base_de_telas_e_login.domain.repository.CategoriaPorta;
import projeto_base_de_telas_e_login.domain.repository.ProdutoPorta;
import java.util.List;

@Service
public class ProdutoUseCase {

    private final ProdutoPorta produtoPorta;
    private final CategoriaPorta categoriaPorta;

    public ProdutoUseCase(
            ProdutoPorta produtoPorta,
            CategoriaPorta categoriaPorta
    ) {
        this.produtoPorta = produtoPorta;
        this.categoriaPorta = categoriaPorta;
    }

    public void save(Product product) {

        if (product.getCategoria() == null ||
                product.getCategoria().getNome_categoria() == null) {
            throw new IllegalArgumentException("Categoria é obrigatória");
        }

        Categoria categoria = categoriaPorta
                .findByNome(product.getCategoria().getNome_categoria())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        product.setCategoria(categoria);

        produtoPorta.save(product);
    }

    public List<Product> findByCategoriaNome(String nomeCategoria) {

        if (nomeCategoria == null || nomeCategoria.isBlank()) {
            throw new IllegalArgumentException("Nome da categoria é obrigatório");
        }

        return produtoPorta.findByCategoriaNome(nomeCategoria);
    }


    public List<Product> findAll() {
        return produtoPorta.findAll();
    }

    public void deleteById(Long id) {
        produtoPorta.deleteById(id);
    }
}
