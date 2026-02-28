package projeto_base_de_telas_e_login.adapter.out.persistence.Estoque;

import org.springframework.stereotype.Component;
import projeto_base_de_telas_e_login.domain.model.estoque.Estoque;
import projeto_base_de_telas_e_login.domain.repository.EstoquePorta;

import java.util.List;
import java.util.Optional;

@Component
public class EstoqueAdapterFake implements EstoquePorta {

    @Override
    public void save(Estoque estoque) {
        // temporário
    }

    @Override
    public Optional<Estoque> buscarPorLojaEProduto(Long lojaId, Long produtoId) {
        return Optional.empty();
    }

    @Override
    public List<Estoque> buscarPorLoja(Long lojaId) {
        return List.of();
    }
}