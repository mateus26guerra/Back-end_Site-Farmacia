package projeto_base_de_telas_e_login.domain.model.Preco;

import java.math.BigDecimal;

public class Preco {

    private BigDecimal valor;
    private BigDecimal desconto; // opcional

    public Preco(BigDecimal valor) {
        this.valor = valor;
        this.desconto = BigDecimal.ZERO; // SEM desconto
    }

    public Preco(BigDecimal valor, BigDecimal desconto) {
        this.valor = valor;
        this.desconto = desconto != null ? desconto : BigDecimal.ZERO;
    }

    public Preco() {

    }

    public boolean temDesconto() {
        return desconto.compareTo(BigDecimal.ZERO) > 0;
    }

    public BigDecimal getValorFinal() {
        return temDesconto()
                ? valor.subtract(desconto)
                : valor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }
}
