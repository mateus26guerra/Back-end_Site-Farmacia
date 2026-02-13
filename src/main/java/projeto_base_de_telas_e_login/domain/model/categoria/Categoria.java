package projeto_base_de_telas_e_login.domain.model.categoria;

import projeto_base_de_telas_e_login.domain.model.product.Product;

import java.util.List;

public class Categoria {

    private Long id;
    private String nomeCategoria;

    public Categoria() {
    }

    public Categoria(Long id, String nomeCategoria) {
        this.id = id;
        this.nomeCategoria = nomeCategoria;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome_categoria() { return nomeCategoria; }
    public void setNome_categoria(String nomeCategoria) { this.nomeCategoria = nomeCategoria; }

}
