package projeto_base_de_telas_e_login.domain.model.Pedido;

import projeto_base_de_telas_e_login.domain.model.ItemPedido.ItemPedido;
import projeto_base_de_telas_e_login.domain.model.Pedido.Enum.Bairro;
import projeto_base_de_telas_e_login.domain.model.Pedido.Enum.FormaDePagamento;
import projeto_base_de_telas_e_login.domain.model.Pedido.Enum.StatusDoPedido;
import projeto_base_de_telas_e_login.domain.model.Pedido.Enum.TipoEntrega;

import java.time.LocalDateTime;
import java.util.List;

public class Pedido {
    private Long id;
    private LocalDateTime criado;
    private String cliente;
    private String telefone;
    private String endereco;
    private Bairro bairro;
    private String complemento;
    private FormaDePagamento formaDePagamento;
    private StatusDoPedido statusDoPedido = StatusDoPedido.Aguardando;
    private List<ItemPedido> itens;
    private Double totalProdutos = 0.0;
    private Double valorFrete = 0.0;
    private Double totalComFrete = 0.0;
    private boolean freteGratis = false;
    private String observacao;
    private TipoEntrega tipoEntrega;
    pri

    // construtor vazio (opcional)
    public Pedido() {
    }

    // construtor básico
    public Pedido(Long id,
                  LocalDateTime criado,
                  String telefone,
                  String endereco,
                  Bairro bairro,
                  String complemento,
                  List<ItemPedido> itens) {
        this.id = id;
        this.criado = criado;
        this.telefone = telefone;
        this.endereco = endereco;
        this.bairro = bairro;
        this.complemento = complemento;
        this.itens = itens;
        this.statusDoPedido = StatusDoPedido.Aguardando;
    }

    // construtor completo
    public Pedido(Long id,
                  LocalDateTime criado,
                  String cliente,
                  String telefone,
                  String endereco,
                  Bairro bairro,
                  String complemento,
                  List<ItemPedido> itens,
                  FormaDePagamento formaDePagamento,
                  TipoEntrega tipoEntrega,
                  String observacao) {

        this.id = id;
        this.criado = criado;
        this.cliente = cliente;
        this.telefone = telefone;
        this.endereco = endereco;
        this.bairro = bairro;
        this.complemento = complemento;
        this.itens = itens;
        this.formaDePagamento = formaDePagamento;
        this.tipoEntrega = tipoEntrega;
        this.observacao = observacao;
        this.statusDoPedido = StatusDoPedido.Aguardando;
    }

    public void calcularTotais() {
        double soma = 0.0;

        if (itens != null) {
            for (ItemPedido item : itens) {
                soma += item.getSubtotal().doubleValue();
            }
        }

        this.totalProdutos = soma;

        if (soma >= 15) {
            this.valorFrete = 0.0;
            this.freteGratis = true;
        } else {
            this.valorFrete = 15.0;
            this.freteGratis = false;
        }

        this.totalComFrete = this.totalProdutos + this.valorFrete;
    }

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getCriado() { return criado; }
    public void setCriado(LocalDateTime criado) { this.criado = criado; }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public Bairro getBairro() { return bairro; }
    public void setBairro(Bairro bairro) { this.bairro = bairro; }

    public String getComplemento() { return complemento; }
    public void setComplemento(String complemento) { this.complemento = complemento; }

    public FormaDePagamento getFormaDePagamento() { return formaDePagamento; }
    public void setFormaDePagamento(FormaDePagamento formaDePagamento) { this.formaDePagamento = formaDePagamento; }

    public StatusDoPedido getStatusDoPedido() { return statusDoPedido; }
    public void setStatusDoPedido(StatusDoPedido statusDoPedido) { this.statusDoPedido = statusDoPedido; }

    public List<ItemPedido> getItens() { return itens; }
    public void setItens(List<ItemPedido> itens) { this.itens = itens; }

    public Double getTotalProdutos() { return totalProdutos; }
    public Double getValorFrete() { return valorFrete; }
    public Double getTotalComFrete() { return totalComFrete; }
    public boolean isFreteGratis() { return freteGratis; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    public TipoEntrega getTipoEntrega() { return tipoEntrega; }
    public void setTipoEntrega(TipoEntrega tipoEntrega) { this.tipoEntrega = tipoEntrega; }
}