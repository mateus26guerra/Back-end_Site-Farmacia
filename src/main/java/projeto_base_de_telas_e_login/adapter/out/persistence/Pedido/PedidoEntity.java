//package projeto_base_de_telas_e_login.adapter.out.persistence.Pedido;
//
//import jakarta.persistence.*;
//import projeto_base_de_telas_e_login.adapter.out.persistence.ItemPedido.ItemPedidoEntity;
//import projeto_base_de_telas_e_login.domain.model.Pedido.Enum.StatusDoPedido;
//import projeto_base_de_telas_e_login.domain.model.Pedido.Enum.TipoEntrega;
//import projeto_base_de_telas_e_login.domain.model.Pedido.Pedido;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Entity
//@Table(name = "pedido")
//public class PedidoEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private Long lojaId;
//
//    private LocalDateTime criadoEm;
//
//    private String nomeCliente;
//    private String telefone;
//    private String endereco;
//    private String bairro;
//
//    @Enumerated(EnumType.STRING)
//    private StatusDoPedido status;
//
//    @Enumerated(EnumType.STRING)
//    private TipoEntrega tipoEntrega;
//
//    @Column(precision = 10, scale = 2)
//    private BigDecimal totalProdutos;
//
//    @Column(precision = 10, scale = 2)
//    private BigDecimal valorFrete;
//
//    @Column(precision = 10, scale = 2)
//    private BigDecimal totalFinal;
//
//    private Boolean freteGratis;
//
//    @OneToMany(
//            mappedBy = "pedido",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private List<ItemPedidoEntity> itens;
//
//    protected PedidoEntity() {}
//
//    // DOMAIN → ENTITY
//    public PedidoEntity(Pedido pedido) {
//        this.id = pedido.getId();
//        this.lojaId = pedido.getLoja().getId(); // pega id da loja
//        this.criadoEm = pedido.getCriadoEm();
//        this.nomeCliente = pedido.getNomeCliente();
//        this.telefone = pedido.getTelefone();
//        this.endereco = pedido.getEndereco();
//        this.bairro = pedido.getBairro();
//        this.status = pedido.getStatus();
//        this.tipoEntrega = pedido.getTipoEntrega();
//
//        // Pega valores de Preco
//        this.totalProdutos = pedido.getTotalProdutos().getValor();
//        this.valorFrete = pedido.getValorFrete().getValor();
//        this.totalFinal = pedido.getTotalFinal().getValor();
//
//        this.freteGratis = pedido.getFreteGratis();
//
//        if (pedido.getItens() != null) {
//            this.itens = pedido.getItens()
//                    .stream()
//                    .map(item -> new ItemPedidoEntity(item, this))
//                    .toList();
//        }
//    }
//
//    // ENTITY → DOMAIN
//    public Pedido toDomain() {
//
//        var itensDomain = this.itens.stream()
//                .map(ItemPedidoEntity::toDomain)
//                .toList();
//
//        // Como o Domain agora usa Loja completo, aqui você precisará só do id da loja
//        // Pode criar um stub simples ou buscar via repository
//        var lojaStub = new projeto_base_de_telas_e_login.domain.model.Loja.Loja();
//        lojaStub.setId(this.lojaId);
//
//        Pedido pedido = new Pedido(
//                lojaStub,
//                this.nomeCliente,
//                this.telefone,
//                this.endereco,
//                this.bairro,
//                this.tipoEntrega,
//                itensDomain
//        );
//
//        // Aplicar valores de Preco
//        pedido.aplicarFrete(new projeto_base_de_telas_e_login.domain.model.Preco.Preco(this.valorFrete));
//        return pedido;
//    }
//
//    // GETTERS básicos
//    public Long getId() { return id; }
//    public Long getLojaId() { return lojaId; }
//    public LocalDateTime getCriadoEm() { return criadoEm; }
//    public String getNomeCliente() { return nomeCliente; }
//    public String getTelefone() { return telefone; }
//    public String getEndereco() { return endereco; }
//    public String getBairro() { return bairro; }
//    public StatusDoPedido getStatus() { return status; }
//    public TipoEntrega getTipoEntrega() { return tipoEntrega; }
//    public BigDecimal getTotalProdutos() { return totalProdutos; }
//    public BigDecimal getValorFrete() { return valorFrete; }
//    public BigDecimal getTotalFinal() { return totalFinal; }
//    public Boolean getFreteGratis() { return freteGratis; }
//    public List<ItemPedidoEntity> getItens() { return itens; }
//}