package projeto_base_de_telas_e_login.domain.UseCase.Loja;

import org.springframework.stereotype.Service;
import projeto_base_de_telas_e_login.adapter.in.web.dto.loja.LojaResponse;
import projeto_base_de_telas_e_login.domain.repository.LojaRepositoryPort;

import java.util.List;

@Service
public class ListLojaUseCase {

    private final LojaRepositoryPort lojaPort;

    public ListLojaUseCase(LojaRepositoryPort lojaPort) {
        this.lojaPort = lojaPort;
    }

    public List<LojaResponse> executar() {
        return lojaPort.listar()
                .stream()
                .map(loja -> new LojaResponse(
                        loja.getId(),
                        loja.getNome(),
                        loja.aceitaEntrega(),   // ✅ aqui usamos o método de conveniência
                        loja.aceitaRetirada()   // ✅ aqui também
                ))
                .toList();
    }
}