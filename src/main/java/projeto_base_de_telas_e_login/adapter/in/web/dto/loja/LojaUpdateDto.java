package projeto_base_de_telas_e_login.adapter.in.web.dto.loja;

import projeto_base_de_telas_e_login.domain.model.Loja.Enum.TipoAtendimentoLoja;

public record LojaUpdateDto(
        String nome,
        String cep,
        String telefone,
        String imageUrl,
        TipoAtendimentoLoja tipoAtendimento,
        String cnpj
) {}