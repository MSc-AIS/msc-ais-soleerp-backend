package msc.ais.soleerp.db;

import msc.ais.soleerp.model.AISInvoice;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 27/2/21.
 */
public interface InvoiceDao {

    String insertInvoice(AISInvoice aisInvoice);

}
