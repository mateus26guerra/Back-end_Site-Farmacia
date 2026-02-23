package projeto_base_de_telas_e_login.adapter.in.web.controllers.telaPedido;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto_base_de_telas_e_login.adapter.in.web.dto.Pedido.ListaDePedidoDTO;
import projeto_base_de_telas_e_login.domain.UseCase.Pedido.PedidoUserCase;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class TelaPedido {
    private final PedidoUserCase pedidoUserCase;

    public TelaPedido(
            PedidoUserCase pedidoUserCase
    ){
        this.pedidoUserCase = pedidoUserCase;
    }

    @GetMapping
    public ResponseEntity<List<ListaDePedidoDTO>> listaDePedidoDoDia(){
        return ResponseEntity.ok(pedidoUserCase.listarTodos()
                .stream()
                .map(ListaDePedidoDTO::fromDomain)
                .toList());
    }

    @GetMapping("/todosPedido")
    public ResponseEntity<List<ListaDePedidoDTO>> todosOsPedidos(){
        return ResponseEntity.ok(pedidoUserCase.listarTodos()
                .stream()
                .map(ListaDePedidoDTO::fromDomain)
                .toList());
    }

    @GetMapping(value = "/{id}/pdf", produces = "application/pdf")
    public ResponseEntity<byte[]> imprimirPDF(@PathVariable Long id){

        byte[] pdf = pedidoUserCase.imprimirPDF(id);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=pedido-" + id + ".pdf")
                .body(pdf);
    }

}
