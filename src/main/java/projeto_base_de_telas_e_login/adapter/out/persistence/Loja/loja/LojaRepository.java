package projeto_base_de_telas_e_login.adapter.out.persistence.Loja.loja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LojaRepository extends JpaRepository<LojaEntity, Long> {
}