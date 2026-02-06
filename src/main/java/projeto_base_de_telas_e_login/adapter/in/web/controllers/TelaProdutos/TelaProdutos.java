package projeto_base_de_telas_e_login.adapter.in.web.controllers.TelaProdutos;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import projeto_base_de_telas_e_login.adapter.in.web.dto.Product.ProductDeletarDto;
import projeto_base_de_telas_e_login.adapter.in.web.dto.Product.ProductListaDto;
import projeto_base_de_telas_e_login.adapter.in.web.dto.Product.ProductResponseDTO;
import projeto_base_de_telas_e_login.adapter.in.web.dto.Product.ProductoAddDto;
import projeto_base_de_telas_e_login.domain.UseCase.Produto.ProdutoUseCase;
import projeto_base_de_telas_e_login.domain.model.product.Product;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class TelaProdutos {

    @Autowired
    private ProdutoUseCase produtoUseCase;

    @PostMapping("/add_products")
    public ResponseEntity<Void> createProduct(@RequestBody ProductoAddDto product) {
        produtoUseCase.save(product.toDomain());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        produtoUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
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
