package projeto_base_de_telas_e_login.domain.repository;

import projeto_base_de_telas_e_login.domain.model.Loja.LojaBairro;

import java.util.List;

public interface LojaBairroRepositoryPort {

    LojaBairro salvar(LojaBairro lojaBairro);

    List<LojaBairro> buscarPorLoja(Long lojaId);
}