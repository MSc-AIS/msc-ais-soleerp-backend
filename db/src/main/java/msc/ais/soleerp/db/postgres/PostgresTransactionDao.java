package msc.ais.soleerp.db.postgres;

import msc.ais.soleerp.db.DBCPDataSource;
import msc.ais.soleerp.db.TransactionDao;
import msc.ais.soleerp.db.jooq.generated.tables.*;
import msc.ais.soleerp.db.jooq.generated.tables.Transaction;
import msc.ais.soleerp.db.jooq.generated.tables.records.TransactionRecord;
import msc.ais.soleerp.db.util.ModelExtractor;
import msc.ais.soleerp.model.AISTransaction;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 27/2/2021.
 */
public class PostgresTransactionDao implements TransactionDao, ModelExtractor {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgresTransactionDao.class);

    @Override
    public Optional<AISTransaction> insertTransaction(AISTransaction transaction) {

        try (Connection connection = DBCPDataSource.getConnection()) {

            DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);
            Transaction t = Transaction.TRANSACTION;

            Record2<Integer, LocalDate> record2 = context.insertInto(t)
                .set(t.TITLE, transaction.getTitle())
                .set(t.ENTITY_ORDER_NO, transaction.getOrderNumber())
                .set(t.COMPANY_FLAG, transaction.getCompanyFlag())
                .set(t.PAYMENT_TERMS, transaction.getPaymentTerms())
                .set(t.STATUS, transaction.getStatus())
                .set(t.ENTITY_ID, transaction.getEntityId())
                .returningResult(t.TRANSACTION_ID, t.DATE_CREATED)
                .fetchOne();

            if (!Objects.isNull(record2)) {
                transaction.setCreatedDate(record2.get(t.DATE_CREATED, LocalDate.class));
                transaction.setId(record2.get(t.TRANSACTION_ID));
                LOGGER.info("Inserted transaction id: " + record2.get(t.TRANSACTION_ID));
                return Optional.of(transaction);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return Optional.empty();
    }

    @Override
    public List<AISTransaction> findTransactions(int userId) {

        List<AISTransaction> transactionList = new ArrayList<>();

        try (Connection connection = DBCPDataSource.getConnection()) {

            DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);
            Transaction t = Transaction.TRANSACTION;
            Entity e = Entity.ENTITY;

            Result<TransactionRecord> records =
                context.select()
                    .from(t.join(e)
                        .on(t.ENTITY_ID.eq(e.ENTITY_ID)))
                    .where(e.USER_ID.eq(userId))
                    .fetchInto(t);

            records.forEach(record -> transactionList.add(extractTransaction(record)));

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        LOGGER.info("Found " + transactionList.size() + " transactions for userId: " + userId);

        return transactionList;
    }

    @Override
    public Optional<AISTransaction> findTransactionById(int id, int userId) {

        AISTransaction transaction = null;

        try (Connection connection = DBCPDataSource.getConnection()) {

            DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);
            Transaction t = Transaction.TRANSACTION;
            TransactionItems ti = TransactionItems.TRANSACTION_ITEMS;
            Item i = Item.ITEM;

            // records contain transaction with items
            Result<Record> records = context.select()
                .from(t
                    .join(ti).on(t.TRANSACTION_ID.eq(ti.TRANSACTION_ID))
                    .join(i).on(ti.ITEM_ID.eq(i.ITEM_ID)))
                .where(i.USER_ID.eq(userId)).and(t.TRANSACTION_ID.eq(id))
                .fetch();

            transaction = extractTransactionWithItems(records);

            LOGGER.info("Transaction with id: " + id + " found and contains "
                + transaction.getTransactionItemList().size() + " item transactions.");

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (NoSuchElementException e) {
            LOGGER.info(e.getMessage());
        }

        return Optional.ofNullable(transaction);
    }

    @Override
    public int deleteTransactionById(int id, int userId) {

        int rowsDeleted = -1;

        try (Connection connection = DBCPDataSource.getConnection()) {

            DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);
            Transaction t = Transaction.TRANSACTION;
            Entity e = Entity.ENTITY;

            rowsDeleted = context
                .deleteFrom(t)
                .where(t.TRANSACTION_ID.eq(id))
                .and(t.ENTITY_ID.in(
                    context
                        .select(e.ENTITY_ID)
                        .from(e)
                        .where(e.USER_ID.eq(userId))))
                .execute();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        LOGGER.info("Rows deleted: " + rowsDeleted);

        return rowsDeleted;
    }

    @Override
    public int updateTransactionById(int id, int userId, AISTransaction transaction) {
        return 0;
    }
}
