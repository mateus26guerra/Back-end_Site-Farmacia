package projeto_base_de_telas_e_login.domain.repository;

import projeto_base_de_telas_e_login.domain.model.Loja.Loja;

import java.util.List;
import java.util.Optional;

public interface LojaPorta {

    void save(Loja loja);

    Optional<Loja> findById(Long id);

    List<Loja> findAll();

    void deleteById(Long id);

    Loja update(Long id,
                Loja dadosAtualizados);
}