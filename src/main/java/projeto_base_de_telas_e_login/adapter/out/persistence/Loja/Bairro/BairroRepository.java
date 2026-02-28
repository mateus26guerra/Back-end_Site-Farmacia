package projeto_base_de_telas_e_login.adapter.out.persistence.Loja.Bairro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BairroRepository extends JpaRepository<BairroEntity, Long> {
}