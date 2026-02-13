package projeto_base_de_telas_e_login.adapter.out.persistence.Pedido;

import org.springframework.stereotype.Component;
import projeto_base_de_telas_e_login.domain.model.Pedido.Pedido;
import projeto_base_de_telas_e_login.domain.repository.PedidoPorta;

import java.util.List;

@Component
public class PedidoAdapter implements PedidoPorta {

    private  PedidoRepository repository;

    public PedidoAdapter(PedidoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Pedido pedido) {
        PedidoEntity entity = new PedidoEntity(pedido);
        repository.save(entity);
    }


    @Override
    public List<Pedido> findAll(){
        return repository.findAll().stream().map(PedidoEntity::toDomain).toList();
    }
}
