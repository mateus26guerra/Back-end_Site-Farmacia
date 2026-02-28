package projeto_base_de_telas_e_login.adapter.in.web.controllers.TelaEstoque;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto_base_de_telas_e_login.adapter.in.web.dto.Estoque.EstoqueListaDto;
import projeto_base_de_telas_e_login.domain.UseCase.EstoqueUseCase;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class TelaEstoque {

    private final EstoqueUseCase useCase;

    public TelaEstoque(EstoqueUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/loja/{lojaId}")
    public ResponseEntity<List<EstoqueListaDto>> listarPorLoja(
            @PathVariable Long lojaId
    ) {
        List<EstoqueListaDto> lista = useCase.buscarPorLoja(lojaId)
                .stream()
                .map(EstoqueListaDto::fromDomain)
                .toList();

        return ResponseEntity.ok(lista);
    }
}