package msc.ais.soleerp.db.postgres;

import msc.ais.soleerp.db.DBCPDataSource;
import msc.ais.soleerp.db.ItemDao;
import msc.ais.soleerp.db.jooq.generated.tables.Item;
import msc.ais.soleerp.db.jooq.generated.tables.records.ItemRecord;
import msc.ais.soleerp.model.AISItem;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 26/2/2021.
 */
public class PostgresItemDao implements ItemDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgresItemDao.class);

    @Override
    public int insertItem(int userId, AISItem item) {

        int itemId = -1;

        try (Connection connection = DBCPDataSource.getConnection()) {

            DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);
            Item i = Item.ITEM;

            ItemRecord record = context.insertInto(i)
                .set(i.USER_ID, userId)
                .set(i.DESCRIPTION, item.getDescription())
                .set(i.MEASUREMENT_CODE, item.getMeasurementCode())
                .set(i.TYPE_CODE, item.getItemCode())
                .returning(i.ITEM_ID)
                .fetchOne();

            if (!Objects.isNull(record)) {
                itemId = record.getItemId();
                LOGGER.info("Inserted item id: " + itemId);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return itemId;
    }

}
