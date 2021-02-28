package msc.ais.soleerp.db;

import msc.ais.soleerp.model.AISInvoice;

import java.util.List;
import java.util.Optional;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 27/2/21.
 */
public interface InvoiceDao {

    Optional<String> insertInvoice(int userId, int transactionId, AISInvoice invoice);

    List<AISInvoice> findInvoices(int userId);

}
