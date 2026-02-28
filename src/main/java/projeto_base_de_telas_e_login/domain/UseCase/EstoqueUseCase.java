package projeto_base_de_telas_e_login.domain.UseCase;

import org.springframework.stereotype.Service;
import projeto_base_de_telas_e_login.domain.model.estoque.Estoque;
import projeto_base_de_telas_e_login.domain.repository.EstoquePorta;

import java.util.List;

@Service
public class EstoqueUseCase {

    private final EstoquePorta estoquePorta;

    public EstoqueUseCase(EstoquePorta estoquePorta) {
        this.estoquePorta = estoquePorta;
    }

    public void adicionarProdutoNaLoja(Estoque estoque) {
        estoquePorta.save(estoque);
    }

    public List<Estoque> buscarPorLoja(Long lojaId) {
        return estoquePorta.buscarPorLoja(lojaId);
    }
}