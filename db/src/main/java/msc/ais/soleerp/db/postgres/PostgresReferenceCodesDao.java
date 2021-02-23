package msc.ais.soleerp.db.postgres;

import msc.ais.soleerp.db.DBCPDataSource;
import msc.ais.soleerp.db.ReferenceCodesDao;
import msc.ais.soleerp.db.jooq.generated.tables.ReferenceCodes;
import msc.ais.soleerp.db.jooq.generated.tables.records.ReferenceCodesRecord;
import msc.ais.soleerp.db.util.ModelExtractor;
import msc.ais.soleerp.model.internal.AISCountry;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 23/2/2021.
 */
public class PostgresReferenceCodesDao implements ReferenceCodesDao, ModelExtractor {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgresReferenceCodesDao.class);

    @Override
    public int[] insertCountries(List<ReferenceCodesRecord> recordList) {

        int[] rows = null;

        try (Connection connection = DBCPDataSource.getConnection()) {

            DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);

            rows = context.batchInsert(recordList)
                .execute();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return rows;
    }

    @Override
    public List<AISCountry> findCountries() {

        final List<AISCountry> countryList = new ArrayList<>();

        try (Connection connection = DBCPDataSource.getConnection()) {

            DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);

            Result<ReferenceCodesRecord> records = context
                .selectFrom(ReferenceCodes.REFERENCE_CODES)
                .where(ReferenceCodes.REFERENCE_CODES.REF_DOMAIN.eq("country_code"))
                .fetch();

            records.forEach(record -> countryList.add(extractCountry(record)));

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return countryList;
    }
}
