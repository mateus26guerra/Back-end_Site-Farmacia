package projeto_base_de_telas_e_login.adapter.in.web.dto.Product;

import projeto_base_de_telas_e_login.domain.model.product.Product;

public record ProductResponseDTO(
        Integer id,
        String name,
        Double price,
        String imagemUrl
) {
    public ProductResponseDTO(Product product) {
        this(product.getId(), product.getName(), product.getPrice(), product.getImagemUrl());
    }
}