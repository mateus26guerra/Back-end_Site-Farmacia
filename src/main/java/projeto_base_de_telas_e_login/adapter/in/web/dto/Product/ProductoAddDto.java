package projeto_base_de_telas_e_login.adapter.in.web.dto.Product;

import projeto_base_de_telas_e_login.domain.model.product.Product;

import java.math.BigDecimal;

public record ProductoAddDto(
        String name,
        Double price,
        String imagemUrl
) {
    public Product toDomain() {
        return new Product(null, name, price, imagemUrl);
    }
}