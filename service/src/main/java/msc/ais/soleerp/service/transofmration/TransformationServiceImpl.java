package msc.ais.soleerp.service.transofmration;

import com.openhtmltopdf.pdfboxout.PdfBoxRenderer;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import msc.ais.soleerp.model.ServiceInvoice;
import msc.ais.soleerp.service.transofmration.conf.FreeMarkerDefaultConfig;
import org.apache.commons.lang3.SystemUtils;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 20/2/2021.
 */
public class TransformationServiceImpl implements TransformationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransformationServiceImpl.class);

    @Override
    public void buildFTL(ServiceInvoice invoice) {
        // Create a data-model
        Map<String, Object> root = new HashMap<>();
        root.put("invoice", invoice);

        try {
            // Get the template (uses cache internally)
            Template template =
                FreeMarkerDefaultConfig
                    .getConfig()
                    .getTemplate("service-invoice-prototype.ftlh");

            // Merge data-model with template
            Writer out = new OutputStreamWriter(System.out);
            template.process(root, out);
        } catch (IOException | TemplateException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public Document transformToW3Doc(ServiceInvoice invoice) {

        Document document = null;

        /* Create a data-model */
        Map<String, Object> ftlhModelMap = new HashMap<>();
        ftlhModelMap.put("invoice", invoice);

        try {
            /* Get the template (uses cache internally) */
            Template template =
                FreeMarkerDefaultConfig
                    .getConfig()
                    .getTemplate("service-invoice-prototype.ftlh");

            /* Merge data-model with template */
            StringWriter stringWriter = new StringWriter();
            template.process(ftlhModelMap, stringWriter);
            stringWriter.flush();
            document = new W3CDom().fromJsoup(Jsoup.parse(stringWriter.toString()));
        } catch (IOException | TemplateException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return document;
    }

    @Override
    public void createPDF(Document document) {
        createPDF(document, "test.pdf");
    }

    @Override
    public void createPDF(Document document, String outputFileName) {

        try {
            OutputStream os =
                new FileOutputStream(
                    SystemUtils.getUserHome() + "/Downloads/" + outputFileName);

            createPDF(document, os);
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void createPDF(Document document, OutputStream os) {

        PdfRendererBuilder pdfRendererBuilder =
            TransformationFactory.createPDFRendererBuilder(document, os);
        PdfBoxRenderer pdfBoxRenderer = pdfRendererBuilder.buildPdfRenderer();
        pdfBoxRenderer.layout();

        try {
            pdfBoxRenderer.createPDF();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
