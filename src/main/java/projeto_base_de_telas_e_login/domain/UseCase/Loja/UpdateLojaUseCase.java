package projeto_base_de_telas_e_login.domain.UseCase.Loja;

import org.springframework.stereotype.Service;
import projeto_base_de_telas_e_login.adapter.in.web.dto.loja.CreateLojaRequest;
import projeto_base_de_telas_e_login.adapter.in.web.dto.loja.LojaResponse;
import projeto_base_de_telas_e_login.domain.model.Loja.Enum.TipoAtendimentoLoja;
import projeto_base_de_telas_e_login.domain.model.Loja.Loja;
import projeto_base_de_telas_e_login.domain.repository.LojaRepositoryPort;

@Service
public class UpdateLojaUseCase {

    private final LojaRepositoryPort lojaPort;

    public UpdateLojaUseCase(LojaRepositoryPort lojaPort) {
        this.lojaPort = lojaPort;
    }

    public LojaResponse executar(Long id, CreateLojaRequest request) {

        // 🔹 Transformar os booleans do request em TipoAtendimentoLoja
        TipoAtendimentoLoja tipoAtendimento;
        if (request.isAceitaEntrega() && request.isAceitaRetirada()) {
            tipoAtendimento = TipoAtendimentoLoja.AMBOS;
        } else if (request.isAceitaEntrega()) {
            tipoAtendimento = TipoAtendimentoLoja.ENTREGA;
        } else if (request.isAceitaRetirada()) {
            tipoAtendimento = TipoAtendimentoLoja.RETIRADA;
        } else {
            throw new IllegalArgumentException("A loja deve aceitar pelo menos entrega ou retirada");
        }

        Loja loja = new Loja(
                id,
                request.getNome(),
                tipoAtendimento
        );

        Loja atualizada = lojaPort.salvar(loja);

        return new LojaResponse(
                atualizada.getId(),
                atualizada.getNome(),
                atualizada.aceitaEntrega(),
                atualizada.aceitaRetirada()
        );
    }
}