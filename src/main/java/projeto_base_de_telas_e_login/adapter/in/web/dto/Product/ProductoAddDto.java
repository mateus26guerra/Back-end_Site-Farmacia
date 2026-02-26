package projeto_base_de_telas_e_login.adapter.in.web.dto.Product;

import projeto_base_de_telas_e_login.domain.model.Preco.Preco;
import projeto_base_de_telas_e_login.domain.model.categoria.Categoria;
import projeto_base_de_telas_e_login.domain.model.product.Product;

import java.math.BigDecimal;

public record ProductoAddDto(
        String name,
        String variacao,
        BigDecimal valor,
        BigDecimal desconto,
        String imagemUrl,
        String categoriaNom) {
    public Product toDomain() {

        Categoria categoria = new Categoria();
        Preco preco = new Preco(
                valor,
                desconto
        );

        return new Product(
                null,
                name,
                variacao,
                preco,
                imagemUrl,
                categoria
        );
    }
}
