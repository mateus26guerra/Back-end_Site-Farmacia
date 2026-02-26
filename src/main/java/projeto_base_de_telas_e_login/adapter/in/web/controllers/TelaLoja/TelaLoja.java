package projeto_base_de_telas_e_login.adapter.in.web.controllers.TelaLoja;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto_base_de_telas_e_login.adapter.in.web.dto.loja.LojaDeletarDto;
import projeto_base_de_telas_e_login.adapter.in.web.dto.loja.LojaUpdateDto;
import projeto_base_de_telas_e_login.adapter.out.persistence.Loja.LojaEntity;
import projeto_base_de_telas_e_login.domain.UseCase.Loja.LojaUseCase;
import projeto_base_de_telas_e_login.domain.model.Loja.Loja;

import java.util.List;

@RestController
@RequestMapping("/lojas")
public class TelaLoja {

    private final LojaUseCase lojaUseCase;

    public TelaLoja(LojaUseCase lojaUseCase) {
        this.lojaUseCase = lojaUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> criarLoja(@RequestBody Loja loja) {
        lojaUseCase.criarLoja(loja);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Loja>> listar() {
        return ResponseEntity.ok(lojaUseCase.listarLojas());
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarLojas(@RequestBody List<LojaDeletarDto> lojas) {
        lojas.forEach(dto -> lojaUseCase.deletarLoja(dto.id()));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Loja> atualizarLoja(
            @PathVariable Long id,
            @RequestBody @Valid LojaUpdateDto dto
    ) {

        Loja lojaAtualizada = lojaUseCase.atualizarLoja(id, dto);
        return ResponseEntity.ok(lojaAtualizada);
    }
}