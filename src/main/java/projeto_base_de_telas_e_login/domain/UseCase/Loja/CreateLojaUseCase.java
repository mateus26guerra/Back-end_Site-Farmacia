package projeto_base_de_telas_e_login.domain.UseCase.Loja;

import org.springframework.stereotype.Service;
import projeto_base_de_telas_e_login.adapter.in.web.dto.loja.CreateLojaRequest;
import projeto_base_de_telas_e_login.adapter.in.web.dto.loja.LojaResponse;
import projeto_base_de_telas_e_login.domain.model.Loja.Loja;
import projeto_base_de_telas_e_login.domain.model.Loja.Enum.TipoAtendimentoLoja;
import projeto_base_de_telas_e_login.domain.repository.LojaRepositoryPort;

@Service
public class CreateLojaUseCase {

    private final LojaRepositoryPort lojaPort;

    public CreateLojaUseCase(LojaRepositoryPort lojaPort) {
        this.lojaPort = lojaPort;
    }

    public LojaResponse executar(CreateLojaRequest request) {

        // Determinar TipoAtendimentoLoja a partir dos booleans enviados
        TipoAtendimentoLoja tipoAtendimento;
        if (request.isAceitaEntrega() && request.isAceitaRetirada()) {
            tipoAtendimento = TipoAtendimentoLoja.AMBOS;
        } else if (request.isAceitaEntrega()) {
            tipoAtendimento = TipoAtendimentoLoja.ENTREGA;
        } else if (request.isAceitaRetirada()) {
            tipoAtendimento = TipoAtendimentoLoja.RETIRADA;
        } else {
            throw new IllegalArgumentException("Loja deve aceitar pelo menos entrega ou retirada");
        }

        // Criar a loja usando o construtor correto do domínio
        Loja loja = new Loja(null, request.getNome(), tipoAtendimento);

        // Salvar no repositório
        Loja salva = lojaPort.salvar(loja);

        // Retornar resposta adaptada ao DTO
        return new LojaResponse(
                salva.getId(),
                salva.getNome(),
                salva.aceitaEntrega(),
                salva.aceitaRetirada()
        );
    }
}