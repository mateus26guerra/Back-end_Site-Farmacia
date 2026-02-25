package projeto_base_de_telas_e_login.adapter.out.persistence.Pedido;

import jakarta.persistence.*;
import projeto_base_de_telas_e_login.adapter.out.persistence.ItemPedido.ItemPedidoEntity;
import projeto_base_de_telas_e_login.domain.model.Pedido.*;
import projeto_base_de_telas_e_login.domain.model.Pedido.Enum.Bairro;
import projeto_base_de_telas_e_login.domain.model.Pedido.Enum.FormaDePagamento;
import projeto_base_de_telas_e_login.domain.model.Pedido.Enum.StatusDoPedido;
import projeto_base_de_telas_e_login.domain.model.Pedido.Enum.TipoEntrega;

import java.math.BigDecimal;
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

    @Enumerated(EnumType.STRING)
    private StatusDoPedido statusDoPedido;

    @Enumerated(EnumType.STRING)
    private TipoEntrega tipoEntrega;

    private String observacao;

    @Column(name = "total_produtos", precision = 10, scale = 2)
    private BigDecimal totalProdutos;

    @Column(name = "valor_frete", precision = 10, scale = 2)
    private BigDecimal valorFrete;

    @Column(name = "total_com_frete", precision = 10, scale = 2)
    private BigDecimal totalComFrete;

    private Boolean freteGratis;

    @OneToMany(
            mappedBy = "pedido",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ItemPedidoEntity> itens;
    private String cep;

    protected PedidoEntity() {}

    // Domain → Entity (salva snapshot do pedido)
    public PedidoEntity(Pedido pedido) {
        this.id = pedido.getId();
        this.criado = pedido.getCriado();
        this.cliente = pedido.getCliente();
        this.telefone = pedido.getTelefone();
        this.endereco = pedido.getEndereco();
        this.bairro = pedido.getBairro();
        this.complemento = pedido.getComplemento();
        this.formaDePagamento = pedido.getFormaDePagamento();
        this.statusDoPedido = pedido.getStatusDoPedido();
        this.tipoEntrega = pedido.getTipoEntrega();
        this.observacao = pedido.getObservacao();

        this.totalProdutos = pedido.getTotalProdutos();
        this.valorFrete = pedido.getValorFrete();
        this.totalComFrete = pedido.getTotalComFrete();
        this.freteGratis = pedido.isFreteGratis();
        this.cep = pedido.getCep();

        if (pedido.getItens() != null) {
            this.itens = pedido.getItens().stream()
                    .map(item -> new ItemPedidoEntity(item, this))
                    .toList();
        }
    }

    // Entity → Domain
    public Pedido toDomain() {
        Pedido pedido = new Pedido();

        pedido.setId(this.id);
        pedido.setCriado(this.criado);
        pedido.setCliente(this.cliente);
        pedido.setTelefone(this.telefone);
        pedido.setEndereco(this.endereco);
        pedido.setBairro(this.bairro);
        pedido.setComplemento(this.complemento);
        pedido.setCep(this.cep);
        pedido.setFormaDePagamento(this.formaDePagamento);
        pedido.setStatusDoPedido(this.statusDoPedido);
        pedido.setTipoEntrega(this.tipoEntrega);
        pedido.setObservacao(this.observacao);

        if (this.itens != null) {
            pedido.setItens(
                    this.itens.stream()
                            .map(ItemPedidoEntity::toDomain)
                            .toList()
            );
        }

        return pedido;
    }
}