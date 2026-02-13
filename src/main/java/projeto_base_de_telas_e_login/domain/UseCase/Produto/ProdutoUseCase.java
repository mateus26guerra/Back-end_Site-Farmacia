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

        if (product.getCategoria() == null || product.getCategoria().getId() == null) {
            throw new IllegalArgumentException("ID da categoria é obrigatório");
        }

        if (product.getQuantidadeEmEstoque() == null || product.getQuantidadeEmEstoque() < 0) {
            throw new IllegalArgumentException("Quantidade em estoque inválida");
        }

        if (product.getPreco() == null) {
            throw new IllegalArgumentException("Produto " + product.getName() + " não possui preço definido!");
        }

        Long categoriaId = product.getCategoria().getId();

        Categoria categoria = categoriaPorta.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        product.setCategoria(categoria);

        produtoPorta.save(product); // aqui a camada de persistência vai criar ProductEntity
    }


    public List<Product> findByCategoria(Long categoriaId) {

        if (categoriaId == null) {
            throw new IllegalArgumentException("ID da categoria é obrigatório");
        }

        return produtoPorta.findByCategoriaId(categoriaId);
    }

    public List<Product> listarPorCategoriaEmEstoque(Long categoriaId) {
        return produtoPorta.findByCategoriaEmEstoque(categoriaId);
    }

    public List<Product> findAll() {
        return produtoPorta.findAll();
    }

    public void deleteById(Long id) {
        produtoPorta.deleteById(id);
    }
}
