package projeto_base_de_telas_e_login.adapter.in.web.dto.loja;

import projeto_base_de_telas_e_login.domain.model.Loja.Enum.TipoAtendimentoLoja;
import projeto_base_de_telas_e_login.domain.model.Loja.Loja;

public record LojaUpdateDto(
        String nome,
        String cep,
        String telefone,
        String imageUrl,
        TipoAtendimentoLoja tipoAtendimento,
        String cnpj
) {
    public Loja toDomain() {
        return new Loja(
                nome,
                cep,
                cnpj, // cnpj não atualizamos aqui
                telefone,
                imageUrl,
                tipoAtendimento
        );
    }
}