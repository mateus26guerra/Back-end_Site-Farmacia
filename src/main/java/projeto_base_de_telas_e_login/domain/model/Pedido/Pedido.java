package projeto_base_de_telas_e_login.domain.model.Pedido;

import projeto_base_de_telas_e_login.domain.model.ItemPedido.ItemPedido;
import projeto_base_de_telas_e_login.domain.model.Loja.Loja;
import projeto_base_de_telas_e_login.domain.model.Pedido.Enum.StatusDoPedido;
import projeto_base_de_telas_e_login.domain.model.Pedido.Enum.TipoEntrega;
import projeto_base_de_telas_e_login.domain.model.Preco.Preco;

import java.time.LocalDateTime;
import java.util.List;

public class Pedido {

    private Long id;
    private Loja loja;

    private String nomeCliente;
    private String telefone;
    private String endereco;
    private String bairro;

    private Preco totalProdutos;
    private Preco valorFrete;
    private Preco totalFinal;

    private TipoEntrega tipoEntrega;
    private StatusDoPedido status;
    private LocalDateTime criadoEm;

    private List<ItemPedido> itens;

    private Boolean freteGratis = false;

    public Pedido(Loja loja,
                  String nomeCliente,
                  String telefone,
                  String endereco,
                  String bairro,
                  TipoEntrega tipoEntrega,
                  List<ItemPedido> itens) {

        if (tipoEntrega == TipoEntrega.ENTREGA && !loja.aceitaEntrega()) {
            throw new IllegalArgumentException("Loja não aceita entrega");
        }

        if (tipoEntrega == TipoEntrega.RETIRADA && !loja.aceitaRetirada()) {
            throw new IllegalArgumentException("Loja não aceita retirada");
        }

        this.loja = loja;
        this.nomeCliente = nomeCliente;
        this.telefone = telefone;
        this.endereco = endereco;
        this.bairro = bairro;
        this.tipoEntrega = tipoEntrega;
        this.itens = itens;

        this.status = StatusDoPedido.AGUARDANDO;
        this.criadoEm = LocalDateTime.now();

        calcularTotais();
    }

    private void calcularTotais() {
        Preco soma = new Preco(java.math.BigDecimal.ZERO);

        for (ItemPedido item : itens) {
            soma = soma.somar(item.getSubtotal());
        }

        this.totalProdutos = soma;
        this.valorFrete = new Preco(java.math.BigDecimal.ZERO);
        this.totalFinal = totalProdutos;
    }

    public void aplicarFrete(Preco frete) {
        this.valorFrete = frete;
        this.totalFinal = totalProdutos.somar(frete);
    }

    public void setFreteGratis(Boolean freteGratis) { this.freteGratis = freteGratis; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Preco getTotalProdutos() {
        return totalProdutos;
    }

    public void setTotalProdutos(Preco totalProdutos) {
        this.totalProdutos = totalProdutos;
    }

    public Preco getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(Preco valorFrete) {
        this.valorFrete = valorFrete;
    }

    public Preco getTotalFinal() {
        return totalFinal;
    }

    public void setTotalFinal(Preco totalFinal) {
        this.totalFinal = totalFinal;
    }

    public TipoEntrega getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(TipoEntrega tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public StatusDoPedido getStatus() {
        return status;
    }

    public void setStatus(StatusDoPedido status) {
        this.status = status;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public Boolean getFreteGratis() {
        return freteGratis;
    }
}