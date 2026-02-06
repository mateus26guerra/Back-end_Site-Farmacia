package projeto_base_de_telas_e_login.adapter.in.web.controllers.TelaInicial;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto_base_de_telas_e_login.adapter.in.web.dto.Product.ProductListaDto;
import projeto_base_de_telas_e_login.domain.UseCase.Produto.ProdutoUseCase;

import java.util.List;

@RestController
@RequestMapping("/productsPublico")
public class TelaInicial {



    private ProdutoUseCase produtoUseCase;

    public TelaInicial(ProdutoUseCase usercaseProduto) {
        this.produtoUseCase = usercaseProduto;
    }

    @GetMapping("/testeDeApiAberta")
    public String teste() {
        return "esse Get está aberto ao público";
    }

    @Operation(summary = "Lista todos os produtos públicos")
    @GetMapping("/list")
    public ResponseEntity<List<ProductListaDto>> getAllProducts() {

        var products = produtoUseCase.findAll();
        var productDtos = products.stream()
                .map(ProductListaDto::fromDomain)
                .toList();

        return ResponseEntity.ok(productDtos);
    }
}
