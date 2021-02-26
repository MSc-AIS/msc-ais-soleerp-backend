package msc.ais.soleerp.db.util;

import msc.ais.soleerp.db.jooq.generated.tables.Transaction;
import msc.ais.soleerp.db.jooq.generated.tables.records.TransactionRecord;
import msc.ais.soleerp.model.AISTransaction;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 27/2/2021.
 */
public interface TransactionModelExtractor {

    default AISTransaction extractTransaction(TransactionRecord record) {
        return AISTransaction.builder()
            .id(record.getTransactionId())
            .companyFlag(record.getCompanyFlag())
            .entityId(record.getEntityId())
            // .orderNumber(!Objects.isNull(record.getEntityOrderNo()) ? record.getEntityOrderNo().intValue() : null)
            .orderNumber(record.get(Transaction.TRANSACTION.ENTITY_ORDER_NO, Integer.class))
            .createdDate(record.get(Transaction.TRANSACTION.DATE_CREATED, LocalDate.class))
            .title(record.getTitle())
            // .totalPrice(!Objects.isNull(record.getTotalPrice()) ? record.getTotalPrice().doubleValue() : null)
            .totalPrice(record.get(Transaction.TRANSACTION.TOTAL_PRICE, Double.class))
            .paymentTerms(record.getPaymentTerms())
            .status(record.getStatus())
            .build();
    }

}
