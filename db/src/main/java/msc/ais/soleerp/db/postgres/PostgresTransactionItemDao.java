package msc.ais.soleerp.db.postgres;

import msc.ais.soleerp.db.DBCPDataSource;
import msc.ais.soleerp.db.TransactionItemDao;
import msc.ais.soleerp.db.jooq.generated.tables.TransactionItems;
import msc.ais.soleerp.db.jooq.generated.tables.records.TransactionItemsRecord;
import msc.ais.soleerp.db.util.JooqSchemaExtractor;
import msc.ais.soleerp.db.util.StoreResult;
import msc.ais.soleerp.model.AISTransactionItem;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 28/2/2021.
 */
public class PostgresTransactionItemDao implements TransactionItemDao, JooqSchemaExtractor {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgresTransactionItemDao.class);

    @Override
    public int insertTransactionItem(AISTransactionItem itemTransaction) {

        int rowsInserted = -1;

        try (Connection connection = DBCPDataSource.getConnection()) {

            DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);
            TransactionItems ti = TransactionItems.TRANSACTION_ITEMS;

            rowsInserted = context.insertInto(ti)
                .set(ti.TRANSACTION_ID, itemTransaction.getTransactionId())
                .set(ti.ITEM_ID, itemTransaction.getItem().getId())
                .set(ti.UNIT_PRICE, BigDecimal.valueOf(itemTransaction.getUnitPrice()))
                .set(ti.DISCOUNT, itemTransaction.getDiscount().shortValue())
                .set(ti.QUANTITY, BigDecimal.valueOf(itemTransaction.getQuantity()))
                .execute();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        LOGGER.info("Rows inserted: " + rowsInserted);

        return rowsInserted;
    }

    @Override
    public StoreResult insertTransactionItems(int transactionId, List<AISTransactionItem> transactionItems) {

        StoreResult storeResult = StoreResult.FAILURE;

        try (Connection connection = DBCPDataSource.getConnection()) {

            DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);
            List<TransactionItemsRecord> records = new ArrayList<>();
            transactionItems.forEach(transactionItem ->
                records.add(extractTransactionItemsRecord(transactionId, transactionItem)));

            int[] rowsInsertedArray =
                context
                    .batchInsert(records)
                    .execute();

            int[] failedInsertionsArray = IntStream.of(rowsInsertedArray)
                .filter(value -> value == 0)
                .toArray();

            LOGGER.info("Succeeded insertions: "
                + (rowsInsertedArray.length - failedInsertionsArray.length)
                + "/" + rowsInsertedArray.length);

            if (failedInsertionsArray.length == 0) {
                storeResult = StoreResult.SUCCESS;
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return storeResult;
    }
}
