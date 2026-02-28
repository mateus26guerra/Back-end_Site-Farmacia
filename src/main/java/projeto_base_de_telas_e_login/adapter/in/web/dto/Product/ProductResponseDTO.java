//package projeto_base_de_telas_e_login.adapter.in.web.dto.Product;
//
//import projeto_base_de_telas_e_login.domain.model.product.Product;
//
//import java.math.BigDecimal;
//public record ProductResponseDTO(
//        Long id,
//        String name,
//        BigDecimal valorFinal,
//        String imagemUrl
//) {
//    public static ProductResponseDTO from(Product product, BigDecimal valorFinal) {
//        return new ProductResponseDTO(
//                product.getId(),
//                product.getName(),
//                valorFinal,
//                product.getImagemUrl()
//        );
//    }
//}