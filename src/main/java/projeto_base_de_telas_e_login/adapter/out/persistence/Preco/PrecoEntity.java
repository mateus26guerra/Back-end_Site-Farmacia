package projeto_base_de_telas_e_login.adapter.out.persistence.Preco;


import jakarta.persistence.*;
import projeto_base_de_telas_e_login.domain.model.Preco.Preco;

import java.math.BigDecimal;

@Entity
@Table(name = "precos")
public class PrecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal valor;

    private BigDecimal desconto;

    public PrecoEntity() {}

    public PrecoEntity(Preco preco) {
        this.valor = preco.getValor();
        this.desconto = preco.getDesconto();
    }

    public Preco toDomain() {
        return new Preco(valor, desconto);
    }
}
