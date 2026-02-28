package projeto_base_de_telas_e_login.adapter.in.web.controllers.TelaLoja;

import org.springframework.web.bind.annotation.*;
import projeto_base_de_telas_e_login.adapter.out.persistence.Loja.Bairro.BairroPersistenceAdapter;
import projeto_base_de_telas_e_login.domain.model.Loja.Bairro;
import projeto_base_de_telas_e_login.domain.repository.BairroRepositoryPort;

import java.util.List;

@RestController
@RequestMapping("/bairros")
public class BairroController {

    private final BairroRepositoryPort bairroPort;

    public BairroController(BairroRepositoryPort bairroPort) {
        this.bairroPort = bairroPort;
    }

    @PostMapping
    public Bairro criar(@RequestBody Bairro bairro) {
        return bairroPort.salvar(bairro);
    }

    @GetMapping
    public List<Bairro> listar() {
        return bairroPort.listar();
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        ((BairroPersistenceAdapter) bairroPort).deletar(id);
    }
}