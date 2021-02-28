package msc.ais.soleerp.service;

import msc.ais.soleerp.model.AISInvoice;

import java.util.List;
import java.util.Optional;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 28/2/2021.
 */
public interface InvoiceService {

    Optional<String> insertInvoice(String tokenId, AISInvoice invoice);

    List<AISInvoice> findInvoices(String tokenId);

}
