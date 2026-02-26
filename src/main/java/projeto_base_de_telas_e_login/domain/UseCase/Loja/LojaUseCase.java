package projeto_base_de_telas_e_login.domain.UseCase.Loja;

import org.springframework.stereotype.Service;
import projeto_base_de_telas_e_login.adapter.in.web.dto.loja.LojaUpdateDto;
import projeto_base_de_telas_e_login.adapter.out.persistence.Loja.LojaEntity;
import projeto_base_de_telas_e_login.domain.model.Loja.Loja;
import projeto_base_de_telas_e_login.domain.repository.LojaPorta;

import java.util.List;

@Service
public class LojaUseCase {

    private final LojaPorta lojaPorta;

    public LojaUseCase(LojaPorta lojaPorta) {
        this.lojaPorta = lojaPorta;
    }

    public void criarLoja(Loja loja) {
        lojaPorta.save(loja);
    }

    public List<Loja> listarLojas() {
        return lojaPorta.findAll();
    }

    public void deletarLoja(Long id) {
        lojaPorta.deleteById(id);
    }

    public Loja atualizarLoja(Long id, LojaUpdateDto dto) {
        Loja lojaAtualizada = dto.toDomain();
        return lojaPorta.update(id, lojaAtualizada);
    }
}