package projeto_base_de_telas_e_login.domain.model.Pedido;

import projeto_base_de_telas_e_login.domain.model.ItemPedido.ItemPedido;
import projeto_base_de_telas_e_login.domain.model.product.Product;

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

    private List<ItemPedido> itens;

    public Pedido(Long id, LocalDateTime criado, String telefone, String endereco, Bairro bairro, String complemento, List<ItemPedido> itens) {
    }

    public Pedido(Long id, LocalDateTime criado, String cliente, String telefone, String endereco, Bairro bairro, String complemento, List<ItemPedido> itens, FormaDePagamento formaDePagamento) {
        this.id = id;
        this.criado = criado;
        this.cliente = cliente;
        this.telefone = telefone;
        this.endereco = endereco;
        this.bairro = bairro;
        this.complemento = complemento;
        this.itens = itens;
        this.formaDePagamento = formaDePagamento;
    }


    public FormaDePagamento getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setProduto(List<ItemPedido> Itens) {
        this.itens = itens;
    }


    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getCriado() {
        return criado;
    }

    public void setCriado(LocalDateTime criado) {
        this.criado = criado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
