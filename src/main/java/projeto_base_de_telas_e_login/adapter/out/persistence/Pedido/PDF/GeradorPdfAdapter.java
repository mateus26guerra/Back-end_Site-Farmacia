package projeto_base_de_telas_e_login.adapter.out.persistence.Pedido.PDF;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.stereotype.Component;
import projeto_base_de_telas_e_login.adapter.out.persistence.Pedido.PDF.PdfTemplatePedido.PdfPedidoTemplate;
import projeto_base_de_telas_e_login.domain.model.Pedido.Pedido;
import projeto_base_de_telas_e_login.domain.repository.GeradorPdfPort;

import java.io.ByteArrayOutputStream;

@Component
public class GeradorPdfAdapter implements GeradorPdfPort {

    @Override
    public byte[] gerarPdfPedido(Pedido pedido) {

        try {

            String html = PdfPedidoTemplate.gerarHtml(pedido);

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(html, null);
            builder.toStream(out);
            builder.run();

            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }
    }
}