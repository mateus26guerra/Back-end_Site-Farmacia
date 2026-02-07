package projeto_base_de_telas_e_login.adapter.in.web.dto.Product;

import projeto_base_de_telas_e_login.domain.model.product.Product;

import java.math.BigDecimal;

public record ProductResponseDTO(
        Integer id,
        String name,
        BigDecimal valor,
        BigDecimal desconto,
        BigDecimal valorFinal,
        String imagemUrl
) {
    public ProductResponseDTO(Product product) {
        this(
                product.getId(),
                product.getName(),
                product.getPreco().getValor(),
                product.getPreco().getDesconto(),
                product.getPreco().getValorFinal(),
                product.getImagemUrl()
        );
    }
}