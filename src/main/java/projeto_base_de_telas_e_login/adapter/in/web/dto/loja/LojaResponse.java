package projeto_base_de_telas_e_login.adapter.in.web.dto.loja;

public class LojaResponse {

    private Long id;
    private String nome;
    private boolean aceitaEntrega;
    private boolean aceitaRetirada;

    public LojaResponse(Long id, String nome, boolean aceitaEntrega, boolean aceitaRetirada) {
        this.id = id;
        this.nome = nome;
        this.aceitaEntrega = aceitaEntrega;
        this.aceitaRetirada = aceitaRetirada;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public boolean isAceitaEntrega() { return aceitaEntrega; }
    public boolean isAceitaRetirada() { return aceitaRetirada; }
}