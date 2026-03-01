package projeto_base_de_telas_e_login.adapter.out.persistence.Loja.loja;

import org.springframework.stereotype.Component;
import projeto_base_de_telas_e_login.domain.model.Loja.Loja;
import projeto_base_de_telas_e_login.domain.repository.LojaRepositoryPort;

import java.util.List;
import java.util.Optional;

@Component
public class LojaPersistenceAdapter implements LojaRepositoryPort {

    private final LojaRepository repository;

    public LojaPersistenceAdapter(LojaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Loja salvar(Loja loja) {
        LojaEntity entity = new LojaEntity(loja);
        return repository.save(entity).toDomain();
    }

    @Override
    public Optional<Loja> buscarPorId(Long id) {
        return repository.findById(id).map(LojaEntity::toDomain);
    }

    @Override
    public List<Loja> listar() {
        return repository.findAll().stream()
                .map(LojaEntity::toDomain)
                .toList();
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }


    @Override
    public Optional<Loja> buscarPorNome(String nome) {
        return repository.findByNome(nome) // supondo que o JpaRepository tem esse método
                .map(LojaEntity::toDomain);
    }
}