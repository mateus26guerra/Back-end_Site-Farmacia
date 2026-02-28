package projeto_base_de_telas_e_login.domain.repository;

import projeto_base_de_telas_e_login.domain.model.Loja.Loja;

import java.util.Optional;
import java.util.List;

public interface LojaRepositoryPort {

    Loja salvar(Loja loja);

    Optional<Loja> buscarPorId(Long id);

    List<Loja> listar();

    void deletar(Long id);
}