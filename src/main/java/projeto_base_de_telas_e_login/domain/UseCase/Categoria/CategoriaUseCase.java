package projeto_base_de_telas_e_login.domain.UseCase.Categoria;

import org.springframework.stereotype.Service;
import projeto_base_de_telas_e_login.domain.model.categoria.Categoria;
import projeto_base_de_telas_e_login.domain.repository.CategoriaPorta;

import java.util.List;
import java.util.Set;

@Service
public class CategoriaUseCase {

    private final CategoriaPorta categoriaPorta;

    // 🔒 categorias fundamentais
    private static final Set<String> CATEGORIAS_PROTEGIDAS = Set.of(
            "Medicamentos",
            "Beleza",
            "Higiene",
            "Infantil",
            "Vitaminas",
            "Promoções"
    );

    public CategoriaUseCase(CategoriaPorta categoriaPorta) {
        this.categoriaPorta = categoriaPorta;
    }

    // 🔹 LISTAR
    public List<Categoria> listarTodas() {
        return categoriaPorta.findAll();
    }

    public Categoria criar(String nome) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome da categoria é obrigatório");
        }

        categoriaPorta.findByNome(nome)
                .ifPresent(c -> {
                    throw new IllegalArgumentException("Categoria já existe");
                });

        Categoria categoria = new Categoria();
        categoria.setNome_categoria(nome);

        return categoriaPorta.save(categoria);



    }

    // 🔹 EDITAR
    public Categoria editar(Long id, String novoNome) {

        Categoria categoria = categoriaPorta.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        if (CATEGORIAS_PROTEGIDAS.contains(categoria.getNome_categoria())) {
            throw new IllegalStateException(
                    "Essa categoria é fundamental e não pode ser editada"
            );
        }

        categoria.setNome_categoria(novoNome);
        return categoriaPorta.save(categoria);
    }

    // 🔹 DELETAR
    public void deletar(Long id) {

        Categoria categoria = categoriaPorta.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        if (CATEGORIAS_PROTEGIDAS.contains(categoria.getNome_categoria())) {
            throw new IllegalStateException(
                    "Essa categoria é fundamental e não pode ser deletada"
            );
        }

        categoriaPorta.deleteById(id);
    }
}