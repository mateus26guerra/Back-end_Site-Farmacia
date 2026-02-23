package projeto_base_de_telas_e_login.adapter.out.persistence.Pedido.PDF.PdfTemplatePedido;

import projeto_base_de_telas_e_login.domain.model.ItemPedido.ItemPedido;
import projeto_base_de_telas_e_login.domain.model.Pedido.Pedido;

import java.text.NumberFormat;
import java.util.Locale;

public class PdfPedidoTemplate {

    public static String gerarHtml(Pedido pedido) {

        NumberFormat moedaBR = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        StringBuilder itensHtml = new StringBuilder();
        double total = 0.0;

        for (ItemPedido item : pedido.getItens()) {


            itensHtml.append("""
                <tr>
                    <td class="produto"></td>
                    <td class="center"></td>
                    <td class="right destaque"></td>
                </tr>
            """);
        }

        String html = """
        <html>
        <head>
        <meta charset="UTF-8"/>
        <style>

        body {
            font-family: Arial, sans-serif;
            background: #f4f6f9;
            padding: 40px;
            color: #1a1d2e;
        }

        .container {
            max-width: 900px;
            margin: auto;
        }

        .header {
            margin-bottom: 30px;
            border-bottom: 3px solid #4361ee;
            padding-bottom: 15px;
        }

        .header h1 {
            margin: 0;
            font-size: 24px;
        }

        .status {
            display: inline-block;
            padding: 5px 12px;
            font-size: 11px;
            font-weight: bold;
            background: #eef1fd;
            color: #4361ee;
            border-radius: 20px;
            margin-left: 10px;
        }

        .info-card {
            background: #ffffff;
            border: 1px solid #e5e9f2;
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 25px;
        }

        .info-card h2 {
            font-size: 14px;
            margin-bottom: 15px;
            border-bottom: 1px solid #e5e9f2;
            padding-bottom: 8px;
        }

        .info-label {
            font-size: 10px;
            font-weight: bold;
            text-transform: uppercase;
            color: #9ba3bf;
        }

        .info-value {
            font-size: 13px;
            margin-bottom: 10px;
        }

        table {
            width: 100%%;
            border-collapse: collapse;
            margin-top: 10px;
        }

        th {
            font-size: 11px;
            text-transform: uppercase;
            color: #6b7494;
            border-bottom: 2px solid #e5e9f2;
            padding: 10px;
            text-align: left;
        }

        td {
            border-bottom: 1px solid #e5e9f2;
            padding: 10px;
            font-size: 13px;
        }

        .center {
            text-align: center;
        }

        .right {
            text-align: right;
        }

        .produto {
            font-weight: bold;
        }

        .destaque {
            color: #ef4444;
            font-weight: bold;
        }

        .total-box {
            margin-top: 25px;
            text-align: right;
        }

        .total-box span {
            font-size: 18px;
            font-weight: bold;
            color: #111827;
        }

        </style>
        </head>

        <body>

        <div class="container">

            <div class="header">
                <h1>
                    Pedido #{{ID}}
                    <span class="status">{{STATUS}}</span>
                </h1>
                <div style="font-size:12px;color:#9ba3bf;">
                    Gerado automaticamente pelo sistema
                </div>
            </div>

            <div class="info-card">
                <h2>Informações do Cliente</h2>

                <div class="info-label">Nome</div>
                <div class="info-value">{{CLIENTE}}</div>

                <div class="info-label">Telefone</div>
                <div class="info-value">{{TELEFONE}}</div>

                <div class="info-label">Endereço</div>
                <div class="info-value">{{ENDERECO}}</div>

                <div class="info-label">Bairro</div>
                <div class="info-value">{{BAIRRO}}</div>

                <div class="info-label">Complemento</div>
                <div class="info-value">{{COMPLEMENTO}}</div>
            </div>

            <div class="info-card">
                <h2>Itens do Pedido</h2>

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
                        {{ITENS}}
                    </tbody>
                </table>

                <div class="total-box">
                    Total do Pedido:
                    <span>{{TOTAL}}</span>
                </div>
            </div>

        </div>

        </body>
        </html>
        """;

        html = html.replace("{{ID}}", String.valueOf(pedido.getId()));
     //   html = html.replace("{{STATUS}}", String.valueOf(pedido.getStatus()));
        html = html.replace("{{CLIENTE}}", pedido.getCliente());
        html = html.replace("{{TELEFONE}}", pedido.getTelefone());
        html = html.replace("{{ENDERECO}}", pedido.getEndereco());
     //   html = html.replace("{{BAIRRO}}", pedido.getBairro());
        html = html.replace("{{COMPLEMENTO}}", pedido.getComplemento());
        html = html.replace("{{ITENS}}", itensHtml.toString());
        html = html.replace("{{TOTAL}}", moedaBR.format(total));

        return html;
    }
}