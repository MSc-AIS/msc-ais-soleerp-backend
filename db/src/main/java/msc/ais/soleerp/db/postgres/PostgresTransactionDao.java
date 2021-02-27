package msc.ais.soleerp.db.postgres;

import msc.ais.soleerp.db.DBCPDataSource;
import msc.ais.soleerp.db.TransactionDao;
import msc.ais.soleerp.db.jooq.generated.tables.Entity;
import msc.ais.soleerp.db.jooq.generated.tables.Item;
import msc.ais.soleerp.db.jooq.generated.tables.Transaction;
import msc.ais.soleerp.db.jooq.generated.tables.TransactionItems;
import msc.ais.soleerp.db.jooq.generated.tables.records.TransactionRecord;
import msc.ais.soleerp.db.util.ModelExtractor;
import msc.ais.soleerp.model.AISTransaction;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 27/2/2021.
 */
public class PostgresTransactionDao implements TransactionDao, ModelExtractor {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgresTransactionDao.class);

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
                + transaction.getItemTransactionList().size() + " item transactions.");

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (NoSuchElementException e) {
            LOGGER.info(e.getMessage());
        }

        return Optional.ofNullable(transaction);
    }
}
