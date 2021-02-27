package msc.ais.soleerp.db;

import msc.ais.soleerp.model.AISTransaction;

import java.util.List;
import java.util.Optional;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 27/2/2021.
 */
public interface TransactionDao {

    List<AISTransaction> findTransactions(int userId);

    Optional<AISTransaction> findTransactionById(int id, int userId);

    int deleteTransactionById(int id, int userId);

    int updateTransactionById(int id, int userId, AISTransaction transaction);

}
