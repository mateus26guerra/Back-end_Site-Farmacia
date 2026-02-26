package projeto_base_de_telas_e_login.adapter.out.persistence.Loja;

import jakarta.persistence.*;
import projeto_base_de_telas_e_login.domain.model.Loja.Enum.TipoAtendimentoLoja;
import projeto_base_de_telas_e_login.domain.model.Loja.Loja;

@Entity
@Table(name = "loja")
public class LojaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Column(nullable = false)
    private String telefone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAtendimentoLoja tipoAtendimento;

    @Column(name = "image_url")
    private String imagemUrl;

    public LojaEntity() {}

    // Domain → Entity
    public LojaEntity(Loja loja) {
        this.id = loja.getId();
        this.nome = loja.getNome();
        this.cep = loja.getCep();
        this.cnpj = loja.getCnpj();
        this.telefone = loja.getTelefone();
        this.imagemUrl = loja.getImagemUrl();
        this.tipoAtendimento = loja.getTipoAtendimento();
    }

    // Entity → Domain
    public Loja toDomain() {
        return new Loja(
                this.id,
                this.nome,
                this.cep,
                this.cnpj,
                this.telefone,
                this.imagemUrl,
                this.tipoAtendimento
        );
    }

    // ======= Adicione estes setters =======
    public void setNome(String nome) { this.nome = nome; }
    public void setCep(String cep) { this.cep = cep; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setImagemUrl(String imagemUrl) { this.imagemUrl = imagemUrl; }
    public void setTipoAtendimento(TipoAtendimentoLoja tipoAtendimento) { this.tipoAtendimento = tipoAtendimento; }
}