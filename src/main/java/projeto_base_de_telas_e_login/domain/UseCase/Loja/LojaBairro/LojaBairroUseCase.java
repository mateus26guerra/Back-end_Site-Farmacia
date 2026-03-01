package projeto_base_de_telas_e_login.domain.UseCase.Loja.LojaBairro;

import org.springframework.stereotype.Service;
import projeto_base_de_telas_e_login.adapter.in.web.dto.loja.lojabairros.CreateLojaBairroRequest;
import projeto_base_de_telas_e_login.adapter.in.web.dto.loja.lojabairros.LojaBairroResponse;
import projeto_base_de_telas_e_login.domain.model.Loja.Bairro;
import projeto_base_de_telas_e_login.domain.model.Loja.Loja;
import projeto_base_de_telas_e_login.domain.model.Loja.LojaBairro;
import projeto_base_de_telas_e_login.domain.repository.BairroRepositoryPort;
import projeto_base_de_telas_e_login.domain.repository.LojaBairroRepositoryPort;
import projeto_base_de_telas_e_login.domain.repository.LojaRepositoryPort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LojaBairroUseCase {

    private final LojaBairroRepositoryPort lojaBairroRepository;
    private final LojaRepositoryPort lojaRepository;
    private final BairroRepositoryPort bairroRepository;

    public LojaBairroUseCase(
            LojaBairroRepositoryPort lojaBairroRepository,
            LojaRepositoryPort lojaRepository,
            BairroRepositoryPort bairroRepository
    ) {
        this.lojaBairroRepository = lojaBairroRepository;
        this.lojaRepository = lojaRepository;
        this.bairroRepository = bairroRepository;
    }

    // Criar LojaBairro
    public LojaBairroResponse criar(CreateLojaBairroRequest request) {
        Loja loja = Optional.ofNullable(request.getLojaId())
                .map(lojaRepository::buscarPorId)
                .orElseGet(() -> request.getLojaNome() != null
                        ? lojaRepository.buscarPorNome(request.getLojaNome())
                        : Optional.empty())
                .orElseThrow(() -> new IllegalArgumentException("Loja não encontrada"));

        Bairro bairro = Optional.ofNullable(request.getBairroId())
                .map(bairroRepository::buscarPorId)
                .orElseGet(() -> request.getBairroNome() != null
                        ? bairroRepository.buscarPorNome(request.getBairroNome())
                        : Optional.empty())
                .orElseThrow(() -> new IllegalArgumentException("Bairro não encontrado"));

        LojaBairro lb = new LojaBairro(loja, bairro, request.getValorFrete());
        LojaBairro salvo = lojaBairroRepository.salvar(lb);

        return LojaBairroResponse.fromDomain(salvo);
    }

    // Listar por loja
    public List<LojaBairroResponse> listarPorLoja(Long lojaId) {
        return lojaBairroRepository.buscarPorLoja(lojaId)
                .stream()
                .map(LojaBairroResponse::fromDomain)
                .collect(Collectors.toList());
    }

    // Listar todas as lojas e bairros
    public List<LojaBairroResponse> listaTodasLojas() {
        return lojaBairroRepository.listaTodasLojas()
                .stream()
                .map(LojaBairroResponse::fromDomain)
                .collect(Collectors.toList());
    }

    // Deletar
    public void deletar(Long id) {
        lojaBairroRepository.deletar(id);
    }
}