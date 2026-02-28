package projeto_base_de_telas_e_login.adapter.in.web.controllers.TelaLoja;

import org.springframework.web.bind.annotation.*;
import projeto_base_de_telas_e_login.adapter.out.persistence.Loja.LojaBairro.LojaBairroPersistenceAdapter;
import projeto_base_de_telas_e_login.domain.model.Loja.LojaBairro;
import projeto_base_de_telas_e_login.domain.repository.LojaBairroRepositoryPort;

import java.util.List;

@RestController
@RequestMapping("/loja-bairros")
public class LojaBairroController {

    private final LojaBairroRepositoryPort port;

    public LojaBairroController(LojaBairroRepositoryPort port) {
        this.port = port;
    }

    @PostMapping
    public LojaBairro criar(@RequestBody LojaBairro lojaBairro) {
        return port.salvar(lojaBairro);
    }

    @GetMapping("/{lojaId}")
    public List<LojaBairro> listarPorLoja(@PathVariable Long lojaId) {
        return port.buscarPorLoja(lojaId);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        ((LojaBairroPersistenceAdapter) port).deletar(id);
    }
}