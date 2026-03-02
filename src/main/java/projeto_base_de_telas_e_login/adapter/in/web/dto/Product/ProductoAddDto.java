package projeto_base_de_telas_e_login.adapter.in.web.dto.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import projeto_base_de_telas_e_login.domain.model.Preco.Preco;
import projeto_base_de_telas_e_login.domain.model.categoria.Categoria;
import projeto_base_de_telas_e_login.domain.model.product.Product;

import java.math.BigDecimal;

public class ProductoAddDto {

    @NotBlank
    private String name;

    private String variacao;

    private String imagemBase64;

    @NotBlank
    private String categoriaNome;

    @NotNull
    private BigDecimal precoVenda;

    // GETTERS E SETTERS
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getVariacao() { return variacao; }
    public void setVariacao(String variacao) { this.variacao = variacao; }

    public String getImagemBase64() { return imagemBase64; }
    public void setImagemBase64(String imagemBase64) { this.imagemBase64 = imagemBase64; }

    public String getCategoriaNome() { return categoriaNome; }
    public void setCategoriaNome(String categoriaNome) { this.categoriaNome = categoriaNome; }

    public BigDecimal getPrecoVenda() { return precoVenda; }
    public void setPrecoVenda(BigDecimal precoVenda) { this.precoVenda = precoVenda; }

    // Converter para domínio
    public Product toDomain() {
        Categoria categoria = new Categoria();
        categoria.setNomeCategoria(this.categoriaNome);

        Preco preco = new Preco(this.precoVenda);

        return new Product(
                this.name,
                this.variacao,
                this.imagemBase64,
                categoria,
                preco
        );
    }
}