package projeto_base_de_telas_e_login.domain.UseCase.Loja;

import org.springframework.stereotype.Service;
import projeto_base_de_telas_e_login.domain.repository.LojaRepositoryPort;

@Service
public class DeleteLojaUseCase {

    private final LojaRepositoryPort lojaPort;

    public DeleteLojaUseCase(LojaRepositoryPort lojaPort) {
        this.lojaPort = lojaPort;
    }

    public void executar(Long id) {
        lojaPort.deletar(id);
    }
}