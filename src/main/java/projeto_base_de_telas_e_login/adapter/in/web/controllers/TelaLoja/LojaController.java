package projeto_base_de_telas_e_login.adapter.in.web.controllers.TelaLoja;

import org.springframework.web.bind.annotation.*;
import projeto_base_de_telas_e_login.adapter.in.web.dto.loja.CreateLojaRequest;
import projeto_base_de_telas_e_login.adapter.in.web.dto.loja.LojaResponse;
import projeto_base_de_telas_e_login.domain.UseCase.Loja.CreateLojaUseCase;
import projeto_base_de_telas_e_login.domain.UseCase.Loja.DeleteLojaUseCase;
import projeto_base_de_telas_e_login.domain.UseCase.Loja.ListLojaUseCase;
import projeto_base_de_telas_e_login.domain.UseCase.Loja.UpdateLojaUseCase;

import java.util.List;

@RestController
@RequestMapping("/lojas")
public class LojaController {

    private final CreateLojaUseCase createUseCase;
    private final ListLojaUseCase listUseCase;
    private final UpdateLojaUseCase updateUseCase;
    private final DeleteLojaUseCase deleteUseCase;

    public LojaController(CreateLojaUseCase createUseCase,
                          ListLojaUseCase listUseCase,
                          UpdateLojaUseCase updateUseCase,
                          DeleteLojaUseCase deleteUseCase) {
        this.createUseCase = createUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public LojaResponse criar(@RequestBody CreateLojaRequest request) {
        return createUseCase.executar(request);
    }

    @GetMapping
    public List<LojaResponse> listar() {
        return listUseCase.executar();
    }

    @PutMapping("/{id}")
    public LojaResponse atualizar(@PathVariable Long id,
                                  @RequestBody CreateLojaRequest request) {
        return updateUseCase.executar(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        deleteUseCase.executar(id);
    }
}