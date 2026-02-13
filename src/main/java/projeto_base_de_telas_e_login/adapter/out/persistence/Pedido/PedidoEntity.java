package projeto_base_de_telas_e_login.adapter.out.persistence.Pedido;

import jakarta.persistence.*;
import projeto_base_de_telas_e_login.adapter.out.persistence.ItemPedido.ItemPedidoEntity;
import projeto_base_de_telas_e_login.domain.model.Pedido.Bairro;
import projeto_base_de_telas_e_login.domain.model.Pedido.FormaDePagamento;
import projeto_base_de_telas_e_login.domain.model.Pedido.Pedido;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedido")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime criado;
    private String cliente;
    private String telefone;
    private String endereco;

    @Enumerated(EnumType.STRING)
    private Bairro bairro;

    private String complemento;

    @Enumerated(EnumType.STRING)
    private FormaDePagamento formaDePagamento;

    // 🔥 RELAÇÃO CORRETA
    @OneToMany(
            mappedBy = "pedido",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ItemPedidoEntity> itens;

    protected PedidoEntity() {}

    // Domain → Entity
    public PedidoEntity(Pedido pedido) {
        this.id = pedido.getId();
        this.criado = pedido.getCriado();
        this.cliente = pedido.getCliente();
        this.telefone = pedido.getTelefone();
        this.endereco = pedido.getEndereco();
        this.bairro = pedido.getBairro();
        this.complemento = pedido.getComplemento();
        this.formaDePagamento = pedido.getFormaDePagamento();

        if (pedido.getItens() != null) {
            this.itens = pedido.getItens().stream()
                    .map(item -> new ItemPedidoEntity(item, this))
                    .toList();
        }

    }

    // Entity → Domain
    public Pedido toDomain() {
        return new Pedido(
                this.id,
                this.criado,
                this.cliente,
                this.telefone,
                this.endereco,
                this.bairro,
                this.complemento,
                this.itens.stream()
                        .map(ItemPedidoEntity::toDomain)
                        .toList(),
                this.formaDePagamento
        );
    }
}
