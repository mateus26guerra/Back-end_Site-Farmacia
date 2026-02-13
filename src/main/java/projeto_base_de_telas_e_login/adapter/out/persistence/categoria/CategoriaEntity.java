package projeto_base_de_telas_e_login.adapter.out.persistence.categoria;

import jakarta.persistence.*;
import projeto_base_de_telas_e_login.domain.model.categoria.Categoria;
import projeto_base_de_telas_e_login.adapter.out.persistence.Product.ProductEntity;

import java.util.List;

@Entity
@Table(name = "categoria")
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_categoria", nullable = false)
    private String nomeCategoria;


    public CategoriaEntity() {
    }

    public CategoriaEntity(Categoria categoria) {
        this.id = categoria.getId();
        this.nomeCategoria = categoria.getNome_categoria();
    }

    public Categoria toDomain() {
        Categoria categoria = new Categoria();
        categoria.setId(this.id);
        categoria.setNome_categoria(this.nomeCategoria);
        return categoria;
    }
}
