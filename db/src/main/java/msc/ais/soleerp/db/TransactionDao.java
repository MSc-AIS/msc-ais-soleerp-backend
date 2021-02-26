package msc.ais.soleerp.db;

import msc.ais.soleerp.model.AISTransaction;

import java.util.List;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 27/2/2021.
 */
public interface TransactionDao {

    List<AISTransaction> findTransactions(int userId);

}
