package projeto_base_de_telas_e_login.adapter.in.web.dto.Product;

public record ProductDeletarDto(
        Integer id) {

    public static ProductDeletarDto fromDomain(ProductDeletarDto product) {
        return new ProductDeletarDto(
            product.id()
        );
    }
}
