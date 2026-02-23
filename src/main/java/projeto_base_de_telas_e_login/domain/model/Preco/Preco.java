package projeto_base_de_telas_e_login.domain.model.Preco;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Preco {

    private BigDecimal valor;
    private BigDecimal desconto;

    public Preco(BigDecimal valor) {
        this.valor = valor;
        this.desconto = BigDecimal.ZERO;
    }

    public Preco(BigDecimal valor, BigDecimal desconto) {
        this.valor = valor;
        this.desconto = desconto != null ? desconto : BigDecimal.ZERO;
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

    public BigDecimal getDesconto() {
        return desconto;
    }

    // 🔥 NOVO — multiplicação
    public Preco multiplicar(int quantidade) {
        BigDecimal resultado = getValorFinal()
                .multiply(BigDecimal.valueOf(quantidade));

        return new Preco(resultado.setScale(2, RoundingMode.HALF_EVEN));
    }

    // 🔥 NOVO — soma
    public Preco somar(Preco outro) {
        BigDecimal resultado = this.getValorFinal()
                .add(outro.getValorFinal());

        return new Preco(resultado.setScale(2, RoundingMode.HALF_EVEN));
    }

    @Override
    public String toString() {
        return "R$ " + getValorFinal().setScale(2, RoundingMode.HALF_EVEN);
    }
}