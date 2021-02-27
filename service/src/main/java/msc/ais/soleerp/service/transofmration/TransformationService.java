package msc.ais.soleerp.service.transofmration;

import msc.ais.soleerp.model.AISInvoice;
import org.w3c.dom.Document;

import java.io.OutputStream;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 20/2/2021.
 */
public interface TransformationService {

    void buildFTL(AISInvoice invoice);

    Document transformToW3Doc(AISInvoice invoice);

    void createPDF(Document document);

    void createPDF(Document document, String outputFileName);

    void createPDF(Document document, OutputStream os);

}
