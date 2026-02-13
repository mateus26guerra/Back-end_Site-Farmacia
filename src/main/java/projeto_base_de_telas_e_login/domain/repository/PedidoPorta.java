package projeto_base_de_telas_e_login.domain.repository;

import projeto_base_de_telas_e_login.domain.model.Pedido.Pedido;

import java.util.List;

public interface PedidoPorta {

    void save(Pedido pedido);

    List<Pedido> findAll();
}
