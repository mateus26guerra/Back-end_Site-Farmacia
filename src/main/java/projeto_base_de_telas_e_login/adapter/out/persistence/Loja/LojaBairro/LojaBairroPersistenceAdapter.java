package projeto_base_de_telas_e_login.adapter.out.persistence.Loja.LojaBairro;

import org.springframework.stereotype.Component;
import projeto_base_de_telas_e_login.adapter.out.persistence.Loja.Bairro.BairroEntity;
import projeto_base_de_telas_e_login.adapter.out.persistence.Loja.Bairro.BairroRepository;
import projeto_base_de_telas_e_login.adapter.out.persistence.Loja.loja.LojaEntity;
import projeto_base_de_telas_e_login.adapter.out.persistence.Loja.loja.LojaRepository;
import projeto_base_de_telas_e_login.domain.model.Loja.LojaBairro;
import projeto_base_de_telas_e_login.domain.repository.LojaBairroRepositoryPort;

import java.util.List;
@Component
public class LojaBairroPersistenceAdapter implements LojaBairroRepositoryPort {

    private final LojaBairroRepository repository;
    private final LojaRepository lojaRepository;
    private final BairroRepository bairroRepository;

    public LojaBairroPersistenceAdapter(
            LojaBairroRepository repository,
            LojaRepository lojaRepository,
            BairroRepository bairroRepository
    ) {
        this.repository = repository;
        this.lojaRepository = lojaRepository;
        this.bairroRepository = bairroRepository;
    }

    @Override
    public LojaBairro salvar(LojaBairro lojaBairro) {

        LojaEntity lojaEntity = lojaRepository
                .findById(lojaBairro.getLoja().getId())
                .orElseThrow();

        BairroEntity bairroEntity = bairroRepository
                .findById(lojaBairro.getBairro().getId())
                .orElseThrow();

        LojaBairroEntity entity =
                new LojaBairroEntity(lojaBairro, lojaEntity, bairroEntity);

        return repository.save(entity).toDomain();
    }

    @Override
    public List<LojaBairro> buscarPorLoja(Long lojaId) {
        return repository.findByLojaId(lojaId)
                .stream()
                .map(LojaBairroEntity::toDomain)
                .toList();
    }

    @Override
    public List<LojaBairro> listaTodasLojas() {
        return repository.findAll()
                .stream()
                .map(LojaBairroEntity::toDomain)
                .toList();
    }


    public void deletar(Long id) {
        repository.deleteById(id);
    }
}