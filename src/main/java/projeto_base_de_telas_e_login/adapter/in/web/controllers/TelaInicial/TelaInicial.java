package projeto_base_de_telas_e_login.adapter.in.web.controllers.TelaInicial;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto_base_de_telas_e_login.adapter.in.web.dto.Pedido.ListaDePedidoDTO;
import projeto_base_de_telas_e_login.adapter.in.web.dto.Pedido.PedidoAddDTO;
import projeto_base_de_telas_e_login.adapter.in.web.dto.Product.ProductListaDto;
import projeto_base_de_telas_e_login.domain.UseCase.Pedido.PedidoUserCase;
import projeto_base_de_telas_e_login.domain.UseCase.Produto.ProdutoUseCase;
import projeto_base_de_telas_e_login.domain.model.Pedido.Pedido;

import java.util.List;

@RestController
@RequestMapping("/productsPublico")
public class TelaInicial {



    private ProdutoUseCase produtoUseCase;

    private PedidoUserCase pedidoUserCase;

    public TelaInicial(ProdutoUseCase produtoUseCase, PedidoUserCase pedidoUserCase) {
        this.produtoUseCase = produtoUseCase;
        this.pedidoUserCase = pedidoUserCase;
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

    @Operation(summary = "Lista produtos por categoria")
    @GetMapping("/list/{categoriaId}")
    public ResponseEntity<List<ProductListaDto>> getProductsByCategoria(
            @PathVariable Long categoriaId
    ) {
        var products = produtoUseCase.findByCategoria(categoriaId);

        var productDtos = products.stream()
                .map(ProductListaDto::fromDomain)
                .toList();

        return ResponseEntity.ok(productDtos);
    }


    @Operation(summary = "Lista produtos por categoria somente se estiverem em estoque")
    @GetMapping("/list/estoque/categoria/{categoriaId}")
    public ResponseEntity<List<ProductListaDto>> listarPorCategoriaEmEstoque(
            @PathVariable Long categoriaId
    ) {
        var products = produtoUseCase.listarPorCategoriaEmEstoque(categoriaId);

        var dtos = products.stream()
                .map(ProductListaDto::fromDomain)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/pedidos")
    public ResponseEntity<Void> criar(@RequestBody PedidoAddDTO dto) {
        pedidoUserCase.criarPedido(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/pedidos")
    public ResponseEntity<List<ListaDePedidoDTO>> listarPedidos() {

        var pedidos = pedidoUserCase.listarTodos();

        var dtos = pedidos.stream()
                .map(ListaDePedidoDTO::fromDomain)
                .toList();

        return ResponseEntity.ok(dtos);
    }

}
