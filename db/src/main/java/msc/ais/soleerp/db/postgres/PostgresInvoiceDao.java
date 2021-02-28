package msc.ais.soleerp.db.postgres;

import msc.ais.soleerp.db.DBCPDataSource;
import msc.ais.soleerp.db.InvoiceDao;
import msc.ais.soleerp.db.jooq.generated.tables.Invoice;
import msc.ais.soleerp.db.jooq.generated.tables.Transaction;
import msc.ais.soleerp.db.util.ModelExtractor;
import msc.ais.soleerp.model.AISInvoice;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 27/2/21.
 */
public class PostgresInvoiceDao implements InvoiceDao, ModelExtractor {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgresInvoiceDao.class);

    @Override
    public Optional<String> insertInvoice(int userId, int transactionId, AISInvoice invoice) {

        try (Connection connection = DBCPDataSource.getConnection()) {

            DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);
            Invoice i = Invoice.INVOICE;

            Record1<String> record1 = context.insertInto(i)
                .set(i.NOTES, invoice.getNotes())
                .set(i.TRANSACTION_ID, transactionId)
                .set(i.USER_ID, userId)
                .returningResult(i.INVOICE_ID)
                .fetchOne();

            if (!Objects.isNull(record1)) {
                LOGGER.info("Inserted invoice id: " + record1.get(i.INVOICE_ID));
                return Optional.ofNullable(record1.get(i.INVOICE_ID));
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return Optional.empty();
    }

    @Override
    public List<AISInvoice> findInvoices(int userId) {

        List<AISInvoice> invoiceList = new ArrayList<>();

        try (Connection connection = DBCPDataSource.getConnection()) {

            DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);
            Invoice i = Invoice.INVOICE;
            Transaction t = Transaction.TRANSACTION;

            Result<Record> records = context.select()
                .from(i
                    .join(t).on(i.TRANSACTION_ID.eq(t.TRANSACTION_ID)))
                .where(i.USER_ID.eq(userId))
                .fetch();

            records.forEach(record -> invoiceList.add(extractInvoiceWithTransaction(record)));

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        LOGGER.info("Found " + invoiceList.size() + " invoices for user with id: " + userId);

        return invoiceList;
    }
}
