package msc.ais.soleerp.db.postgres;

import msc.ais.soleerp.db.DBCPDataSource;
import msc.ais.soleerp.db.InvoiceDao;
import msc.ais.soleerp.db.jooq.generated.tables.Invoice;
import msc.ais.soleerp.model.AISInvoice;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 27/2/21.
 */
public class PostgresInvoiceDao implements InvoiceDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgresInvoiceDao.class);

    @Override
    public String insertInvoice(AISInvoice aisInvoice) {

        String invoiceId = null;

        try (Connection connection = DBCPDataSource.getConnection()) {

            DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);
            Invoice i = Invoice.INVOICE;

            context.insertInto(i)
                .set

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return Optional.ofNullable(invoiceId);
    }

}
