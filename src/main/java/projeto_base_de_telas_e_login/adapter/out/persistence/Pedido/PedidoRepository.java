package projeto_base_de_telas_e_login.adapter.out.persistence.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PedidoRepository extends JpaRepository<PedidoEntity,Long> {

    List<PedidoEntity> findByCriadoBetweenOrderByCriadoDesc(
            LocalDateTime inicio,
            LocalDateTime fim
    );
}
