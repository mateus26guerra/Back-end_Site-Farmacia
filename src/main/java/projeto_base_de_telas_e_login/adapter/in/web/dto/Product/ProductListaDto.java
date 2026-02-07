package projeto_base_de_telas_e_login.adapter.in.web.dto.Product;

import projeto_base_de_telas_e_login.domain.model.Preco.Preco;
import projeto_base_de_telas_e_login.domain.model.categoria.Categoria;
import projeto_base_de_telas_e_login.domain.model.product.Product;

import java.math.BigDecimal;

public record ProductListaDto(
    Integer id,
    String name,
    Preco preco,
    String imagemUrl,
    Categoria categoria
) {
    public static ProductListaDto fromDomain(Product product) {
        return new ProductListaDto(
                product.getId(),
                product.getName(),
                product.getPreco(),
                product.getImagemUrl(),
                product.getCategoria()
        );
    }
}
