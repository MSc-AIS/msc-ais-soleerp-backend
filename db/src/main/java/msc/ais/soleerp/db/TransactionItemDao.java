package msc.ais.soleerp.db;

import msc.ais.soleerp.db.util.StoreResult;
import msc.ais.soleerp.model.AISTransactionItem;

import java.util.List;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 28/2/2021.
 */
public interface TransactionItemDao {

    int insertTransactionItem(AISTransactionItem transactionItem);

    StoreResult insertTransactionItems(int transactionId, List<AISTransactionItem> transactionItems);
}
