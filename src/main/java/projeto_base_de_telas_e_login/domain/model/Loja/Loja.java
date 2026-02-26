package projeto_base_de_telas_e_login.domain.model.Loja;

import projeto_base_de_telas_e_login.domain.model.Loja.Enum.TipoAtendimentoLoja;

import java.util.Objects;

public class Loja {

    private Long id;
    private String nome;
    private String cep;
    private String cnpj;
    private String telefone;
    private TipoAtendimentoLoja tipoAtendimento;
    private String imagemUrl;

    public Loja() {
    }

    public Loja(Long id,
                String nome,
                String cep,
                String cnpj,
                String telefone,
                String imagemUrl,
                TipoAtendimentoLoja tipoAtendimento) {

        this.id = id;
        this.nome = nome;
        this.cep = cep;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.tipoAtendimento = tipoAtendimento;
        this.imagemUrl = imagemUrl;
    }

    public Loja(String nome,
                String cep,
                String cnpj,
                String telefone,
                String imagemUrl,
                TipoAtendimentoLoja tipoAtendimento)
        {
        this(null, nome, cep, cnpj, telefone,imagemUrl, tipoAtendimento);
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCep() {
        return cep;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public TipoAtendimentoLoja getTipoAtendimento() {
        return tipoAtendimento;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagemUrl() { return imagemUrl; }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }}