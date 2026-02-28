package projeto_base_de_telas_e_login.adapter.out.persistence.Loja.LojaBairro;

import jakarta.persistence.*;
import projeto_base_de_telas_e_login.adapter.out.persistence.Loja.Bairro.BairroEntity;
import projeto_base_de_telas_e_login.adapter.out.persistence.Loja.loja.LojaEntity;
import projeto_base_de_telas_e_login.domain.model.Loja.LojaBairro;

import java.math.BigDecimal;

@Entity
@Table(name = "loja_bairro")
public class LojaBairroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "loja_id", nullable = false)
    private LojaEntity loja;

    @ManyToOne
    @JoinColumn(name = "bairro_id", nullable = false)
    private BairroEntity bairro;

    private BigDecimal valorFrete;

    protected LojaBairroEntity() {}

    public LojaBairroEntity(LojaBairro model, LojaEntity loja, BairroEntity bairro) {
        this.loja = loja;
        this.bairro = bairro;
        this.valorFrete = model.getValorFrete();
    }

    public LojaBairro toDomain() {
        return new LojaBairro(
                loja.toDomain(),
                bairro.toDomain(),
                valorFrete
        );
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public BairroEntity getBairro() {
        return bairro;
    }
}