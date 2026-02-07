package projeto_base_de_telas_e_login.adapter.in.web.controllers.TelaProdutos;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import projeto_base_de_telas_e_login.adapter.in.web.dto.Categoria.CriarCategoriaDTO;import projeto_base_de_telas_e_login.adapter.in.web.dto.Product.ProductListaDto;
import projeto_base_de_telas_e_login.adapter.in.web.dto.Product.ProductoAddDto;
import projeto_base_de_telas_e_login.adapter.out.persistence.categoria.CategoriaEntity;
import projeto_base_de_telas_e_login.adapter.out.persistence.categoria.CategoriaRepository;
import projeto_base_de_telas_e_login.domain.UseCase.Produto.ProdutoUseCase;
import projeto_base_de_telas_e_login.domain.model.categoria.Categoria;
import projeto_base_de_telas_e_login.domain.model.product.Product;

import java.util.List;

@RestController
@RequestMapping("products")
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class TelaProdutos {

    @Autowired
    private ProdutoUseCase produtoUseCase;

    @Autowired
    private CategoriaRepository categoriaRepository;


    // 🔐 CRIAR CATEGORIA (ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add_Categoria")
    public ResponseEntity<Void> criarCategoria(
            @RequestBody @Valid CriarCategoriaDTO dto
    ) {
        Categoria categoria = new Categoria();
        categoria.setNome_categoria(dto.nomeCategoria());

        CategoriaEntity entity = new CategoriaEntity(categoria);
        categoriaRepository.save(entity);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/add_products")
    public ResponseEntity<Void> createProduct(@RequestBody ProductoAddDto dto) {

        Product product = dto.toDomain();
        produtoUseCase.save(product);

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
