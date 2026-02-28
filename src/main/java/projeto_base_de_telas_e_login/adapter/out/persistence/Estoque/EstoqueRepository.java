package projeto_base_de_telas_e_login.adapter.out.persistence.Estoque;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<EstoqueEntity, Long> {

    List<EstoqueEntity> findByLoja_Id(Long lojaId);

    Optional<EstoqueEntity> findByLoja_IdAndProduto_Id(Long lojaId, Long produtoId);
}