package projeto_base_de_telas_e_login.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto_base_de_telas_e_login.adapter.out.persistence.Preco.PrecoEntity;

public interface PrecoPorta extends JpaRepository<PrecoEntity,Long> {


}
