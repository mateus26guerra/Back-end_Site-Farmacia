package projeto_base_de_telas_e_login.adapter.out.persistence.Product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    List<ProductEntity> findByCategoria_Id(Integer categoriaId);
    List<ProductEntity> findByCategoria_IdAndTemEmEstoqueTrue(Integer categoriaId);

}
