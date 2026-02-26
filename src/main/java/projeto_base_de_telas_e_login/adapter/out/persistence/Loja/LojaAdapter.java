package projeto_base_de_telas_e_login.adapter.out.persistence.Loja;

import org.springframework.stereotype.Component;
import projeto_base_de_telas_e_login.domain.model.Loja.Loja;
import projeto_base_de_telas_e_login.domain.repository.LojaPorta;

import java.util.List;
import java.util.Optional;

@Component
public class LojaAdapter implements LojaPorta {

    private final LojaRepository repository;

    public LojaAdapter(LojaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Loja loja) {
        LojaEntity entity = new LojaEntity(loja);
        repository.save(entity);
    }

    @Override
    public Optional<Loja> findById(Long id) {
        return repository.findById(id)
                .map(LojaEntity::toDomain);
    }

    @Override
    public List<Loja> findAll() {
        return repository.findAll()
                .stream()
                .map(LojaEntity::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Loja update(Long id, Loja dadosAtualizados) {

        LojaEntity lojaExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loja não encontrada"));

        lojaExistente.setNome(dadosAtualizados.getNome());
        lojaExistente.setCep(dadosAtualizados.getCep());
        lojaExistente.setTelefone(dadosAtualizados.getTelefone());
        lojaExistente.setImagemUrl(dadosAtualizados.getImagemUrl());
        lojaExistente.setTipoAtendimento(dadosAtualizados.getTipoAtendimento());

        LojaEntity salvo = repository.save(lojaExistente);
        return salvo.toDomain();
    }
}