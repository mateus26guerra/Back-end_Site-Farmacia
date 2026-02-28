package projeto_base_de_telas_e_login.adapter.out.persistence.Estoque;

import org.springframework.stereotype.Component;
import projeto_base_de_telas_e_login.domain.model.estoque.Estoque;
import projeto_base_de_telas_e_login.domain.repository.EstoquePorta;

import java.util.List;
import java.util.Optional;
@Component
public class EstoqueAdapter implements EstoquePorta {

    private final EstoqueRepository repository;

    public EstoqueAdapter(EstoqueRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Estoque estoque) {
        // depois você pode melhorar isso
        EstoqueEntity entity = new EstoqueEntity();
        repository.save(entity);
    }

    @Override
    public Optional<Estoque> buscarPorLojaEProduto(Long lojaId, Long produtoId) {
        return repository.findByLoja_IdAndProduto_Id(lojaId, produtoId)
                .map(this::toDomain);
    }

    @Override
    public List<Estoque> buscarPorLoja(Long lojaId) {
        return repository.findByLoja_Id(lojaId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    private Estoque toDomain(EstoqueEntity entity) {
        return new Estoque(
                entity.getId(),
                entity.getLoja().toDomain(),
                entity.getProduto().toDomain(),
                entity.getQuantidade(),
                new projeto_base_de_telas_e_login.domain.model.Preco.Preco(entity.getPrecoVenda())
        );
    }
}