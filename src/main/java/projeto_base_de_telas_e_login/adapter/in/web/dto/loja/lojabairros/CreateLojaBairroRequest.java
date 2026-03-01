package projeto_base_de_telas_e_login.adapter.in.web.dto.loja.lojabairros;

import java.math.BigDecimal;

public class CreateLojaBairroRequest {

    private Long lojaId;
    private Long bairroId;
    private BigDecimal valorFrete;

    public Long getLojaId() { return lojaId; }
    public void setLojaId(Long lojaId) { this.lojaId = lojaId; }

    public Long getBairroId() { return bairroId; }
    public void setBairroId(Long bairroId) { this.bairroId = bairroId; }

    public BigDecimal getValorFrete() { return valorFrete; }
    public void setValorFrete(BigDecimal valorFrete) { this.valorFrete = valorFrete; }
}