package msc.ais.soleerp.db.util;

import msc.ais.soleerp.db.jooq.generated.tables.Item;
import msc.ais.soleerp.db.jooq.generated.tables.Transaction;
import msc.ais.soleerp.db.jooq.generated.tables.records.TransactionRecord;
import msc.ais.soleerp.model.AISItem;
import msc.ais.soleerp.model.AISTransaction;
import org.jooq.Record;
import org.jooq.Result;

import java.time.LocalDate;
import java.util.NoSuchElementException;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 27/2/2021.
 */
public interface TransactionModelExtractor {

    default AISTransaction extractTransaction(TransactionRecord record) {
        return AISTransaction.builder()
            .id(record.getTransactionId())
            .companyFlag(record.getCompanyFlag())
            .entityId(record.getEntityId())
            .orderNumber(record.get(Transaction.TRANSACTION.ENTITY_ORDER_NO, Integer.class))
            .createdDate(record.get(Transaction.TRANSACTION.DATE_CREATED, LocalDate.class))
            .title(record.getTitle())
            .totalPrice(record.get(Transaction.TRANSACTION.TOTAL_PRICE, Double.class))
            .paymentTerms(record.getPaymentTerms())
            .status(record.getStatus())
            .build();
    }

    default AISTransaction extractTransaction(Record record) {
        Transaction t = Transaction.TRANSACTION;
        return AISTransaction.builder()
            .id(record.get(t.TRANSACTION_ID))
            .companyFlag(record.get(t.COMPANY_FLAG))
            .entityId(record.get(t.ENTITY_ID))
            .orderNumber(record.get(t.ENTITY_ORDER_NO, Integer.class))
            .createdDate(record.get(t.DATE_CREATED, LocalDate.class))
            .title(record.get(t.TITLE))
            .totalPrice(record.get(t.TOTAL_PRICE, Double.class))
            .paymentTerms(record.get(t.PAYMENT_TERMS))
            .status(record.get(t.STATUS))
            .build();
    }

    default AISTransaction extractTransactionWithItems(Result<Record> records) {

        if (records.size() < 1) {
            throw new NoSuchElementException("Result does not contain any records");
        }

        // Extract the transaction
        final AISTransaction transaction = extractTransaction(records.get(0));

        // Extract the transaction items
        records.forEach(record -> transaction.getItemList().add(extractAISItem(record)));

        return transaction;
    }

    default AISItem extractAISItem(Record record) {
        Item i = Item.ITEM;
        return AISItem.builder()
            .id(record.get(i.ITEM_ID))
            .typeCode(record.get(i.TYPE_CODE))
            .measurementCode(record.get(i.MEASUREMENT_CODE))
            .createdDate(record.get(Item.ITEM.DATE_CREATED, LocalDate.class))
            .firstSoldDate(record.get(Item.ITEM.DATE_FIRST_SOLD, LocalDate.class))
            .userId(record.get(i.USER_ID))
            .description(record.get(i.DESCRIPTION))
            .build();
    }

}
