package projeto_base_de_telas_e_login.adapter.in.web.controllers.TelaProdutos;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import projeto_base_de_telas_e_login.adapter.in.web.dto.Categoria.CriarCategoriaDTO;
import projeto_base_de_telas_e_login.adapter.in.web.dto.Product.ProductoAddDto;
import projeto_base_de_telas_e_login.domain.UseCase.Categoria.CategoriaUseCase;
import projeto_base_de_telas_e_login.domain.UseCase.Produto.ProdutoUseCase;
import projeto_base_de_telas_e_login.domain.model.categoria.Categoria;
import projeto_base_de_telas_e_login.domain.repository.CategoriaPorta;


@RestController
@RequestMapping("products")
public class TelaProdutos {

    private final ProdutoUseCase produtoUseCase;
    private final CategoriaPorta categoriaPorta;
    private final CategoriaUseCase categoriaUseCase;

    public TelaProdutos(
            ProdutoUseCase produtoUseCase,
            CategoriaPorta categoriaPorta,
            CategoriaUseCase categoriaUseCase

    ) {
        this.produtoUseCase = produtoUseCase;
        this.categoriaPorta = categoriaPorta;
        this.categoriaUseCase = categoriaUseCase;
    }


    @PostMapping
    public ResponseEntity<Void> criarProduto(
            @RequestBody @Valid ProductoAddDto dto
    ) {
        produtoUseCase.save(dto.toDomain());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoUseCase.deleteById(id);
        return ResponseEntity.noContent().build();

    }    }