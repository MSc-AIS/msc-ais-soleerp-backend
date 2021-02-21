package msc.ais.soleerp.db.postgres;

import msc.ais.soleerp.db.DBCPDataSource;
import msc.ais.soleerp.db.EntityDao;
import msc.ais.soleerp.db.jooq.generated.tables.VEntity;
import msc.ais.soleerp.db.jooq.generated.tables.records.VEntityRecord;
import msc.ais.soleerp.db.util.ModelExtractor;
import msc.ais.soleerp.model.AISEntity;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 21/2/21.
 */
public class PostgresEntityDao implements EntityDao, ModelExtractor {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgresEntityDao.class);

    @Override
    public List<AISEntity> findEntities(String tokenId) {

        final List<AISEntity> entityList = new ArrayList<>();

        try (Connection connection = DBCPDataSource.getConnection()) {

            DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);

            List<VEntityRecord> entityRecordList = context
                .selectFrom(VEntity.V_ENTITY)
                .where(VEntity.V_ENTITY.TOKEN.eq(tokenId))
                .fetch();

            entityRecordList.forEach(vEntityRecord -> entityList.add(extractAISEntity(vEntityRecord)));

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return entityList;
    }

}
