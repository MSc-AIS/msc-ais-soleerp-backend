package msc.ais.soleerp.db.util;

import msc.ais.soleerp.db.jooq.generated.tables.records.TransactionItemsRecord;
import msc.ais.soleerp.model.AISTransactionItem;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 28/2/2021.
 */
public interface JooqSchemaExtractor {

    default TransactionItemsRecord extractTransactionItemsRecord(int transactionId,
                                                                 AISTransactionItem transactionItem) {

        TransactionItemsRecord record = new TransactionItemsRecord();
        record.setTransactionId(transactionId);
        record.setItemId(transactionItem.getItem().getId());
        record.setDiscount(!Objects.isNull(transactionItem.getDiscount())
            ? transactionItem.getDiscount().shortValue()
            : null);
        record.setQuantity(BigDecimal.valueOf(transactionItem.getQuantity()));
        record.setUnitPrice(BigDecimal.valueOf(transactionItem.getUnitPrice()));

        return record;
    }

}
