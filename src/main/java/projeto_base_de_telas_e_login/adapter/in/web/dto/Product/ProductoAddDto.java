package projeto_base_de_telas_e_login.adapter.in.web.dto.Product;

import projeto_base_de_telas_e_login.domain.model.Preco.Preco;
import projeto_base_de_telas_e_login.domain.model.categoria.Categoria;
import projeto_base_de_telas_e_login.domain.model.product.Product;

import java.math.BigDecimal;

public record ProductoAddDto(
        String name,
        String variacao,
        String imagemUrl,
        String categoriaNom,
        BigDecimal preco // 👈 adicionamos o preco no DTO
) {

    public Product toDomain() {

        Categoria categoria = new Categoria();
        categoria.setNomeCategoria(categoriaNom);

        Preco precoVenda = new Preco(preco); // converter BigDecimal para Preco

        return new Product(
                name,
                variacao,
                imagemUrl,
                categoria,
                precoVenda
        );
    }
}