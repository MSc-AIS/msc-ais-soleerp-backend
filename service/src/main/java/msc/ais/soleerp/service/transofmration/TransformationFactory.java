package msc.ais.soleerp.service.transofmration;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import msc.ais.soleerp.service.conf.ServiceConfig;
import org.w3c.dom.Document;

import java.io.OutputStream;
import java.nio.file.Paths;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 20/2/2021.
 */
public class TransformationFactory {

    public static PdfRendererBuilder createPDFRendererBuilder(Document document, OutputStream os) {

        return new PdfRendererBuilder()
            .toStream(os)
            .useFont(
                Paths.get("src/main/resources/templates/fonts/" + ServiceConfig.getFont()).toFile(),
                "source-sans")
            .withW3cDocument(document, null);
    }
}
