package projeto_base_de_telas_e_login.adapter.in.web.dto.loja;

public class CreateLojaRequest {

    private String nome;
    private boolean aceitaEntrega;
    private boolean aceitaRetirada;

    public String getNome() { return nome; }
    public boolean isAceitaEntrega() { return aceitaEntrega; }
    public boolean isAceitaRetirada() { return aceitaRetirada; }
}