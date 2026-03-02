package projeto_base_de_telas_e_login.adapter.out.persistence.Pedido.PDF.PdfTemplatePedido;

import projeto_base_de_telas_e_login.domain.model.ItemPedido.ItemPedido;
import projeto_base_de_telas_e_login.domain.model.Pedido.Pedido;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PdfPedidoTemplate {

    public static String gerarHtml(Pedido pedido) {

        NumberFormat moedaBR =
                NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        StringBuilder itensHtml = new StringBuilder();

        for (ItemPedido item : pedido.getItens()) {
            itensHtml.append("""
                <tr>
                    <td class="produto">%s</td>
                    <td class="center">%d</td>
                    <td class="right">%s</td>
                    <td class="right destaque">%s</td>
                </tr>
            """.formatted(
                    item.getNomeProduto(),
                    item.getQuantidade(),
                    moedaBR.format(item.getPrecoUnitario().getValor()),
                    moedaBR.format(item.getSubtotal().getValor())
            ));
        }
        String freteInfo = Boolean.TRUE.equals(pedido.getFreteGratis())
                ? "Grátis"
                : (pedido.getValorFrete() != null ? moedaBR.format(pedido.getValorFrete().getValor()) : "R$ 0,00");

        String html = """
        <html>
        <head>
        <meta charset="UTF-8"/>
        <style>
        body { font-family: Arial; padding:40px; }
        table { width:100%%; border-collapse: collapse; }
        th, td { padding:10px; border-bottom:1px solid #ddd; }
        .right { text-align:right; }
        .center { text-align:center; }
        .produto { font-weight:bold; }
        .destaque { font-weight:bold; color:#ef4444; }
        .label { color:#666; font-size:0.9em; }
        </style>
        </head>
        <body>

        <h2>Pedido #%s</h2>
        <p><b>Status:</b> %s</p>
        <p><b>Tipo de Entrega:</b> %s</p>
        <p><b>Data:</b> %s</p>

        <h3>Cliente</h3>
        <p><b>Nome:</b> %s</p>
        <p><b>Email:</b> %s</p>
        <p><b>Telefone:</b> %s</p>
        <p><b>Endereço:</b> %s</p>
        <p><b>Bairro:</b> %s</p>
        <p><b>Complemento:</b> %s</p>
        <p><b>Observação:</b> %s</p>

        <h3>Itens</h3>
        <table>
            <thead>
                <tr>
                    <th>Produto</th>
                    <th>Qtd</th>
                    <th>Preço</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                %s
            </tbody>
        </table>

        <br/>
        <table style="width:40%%; margin-left:auto;">
            <tr>
                <td>Total Produtos:</td>
                <td class="right">%s</td>
            </tr>
            <tr>
                <td>Frete:</td>
                <td class="right">%s</td>
            </tr>
            <tr>
                <td><b>Total Final:</b></td>
                <td class="right destaque">%s</td>
            </tr>
        </table>

        </body>
        </html>
        """.formatted(
                pedido.getId(),
                pedido.getStatus().name(),
                pedido.getTipoEntrega().name(),
                pedido.getCriadoEm().format(fmt),
                pedido.getNomeCliente(),
                pedido.getEmail(),
                pedido.getTelefone(),
                pedido.getEndereco(),
                pedido.getBairro(),
                pedido.getComplemento() != null ? pedido.getComplemento() : "-",
                pedido.getObservacao() != null ? pedido.getObservacao() : "-",
                itensHtml.toString(),
                moedaBR.format(pedido.getTotalProdutos().getValor()),
                freteInfo,
                moedaBR.format(pedido.getTotalFinal().getValor())
        );

        return html;
    }
}