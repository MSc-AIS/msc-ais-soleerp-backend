package msc.ais.soleerp.service;

import msc.ais.soleerp.model.AISTransaction;

import java.util.List;
import java.util.Optional;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 27/2/2021.
 */
public interface TransactionService {

    List<AISTransaction> findTransactions(String tokenId);

    Optional<AISTransaction> findTransactionById(int id, String tokenId);

}
