package msc.ais.soleerp.db.postgres;

import msc.ais.soleerp.db.DBCPDataSource;
import msc.ais.soleerp.db.EntityDao;
import msc.ais.soleerp.db.jooq.generated.tables.BankAccount;
import msc.ais.soleerp.db.jooq.generated.tables.Entity;
import msc.ais.soleerp.db.jooq.generated.tables.VEntity;
import msc.ais.soleerp.db.jooq.generated.tables.records.BankAccountRecord;
import msc.ais.soleerp.db.jooq.generated.tables.records.EntityRecord;
import msc.ais.soleerp.db.jooq.generated.tables.records.VEntityRecord;
import msc.ais.soleerp.db.util.ModelExtractor;
import msc.ais.soleerp.model.AISEntity;
import msc.ais.soleerp.model.NaturalAISEntity;
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
import java.util.Map;
import java.util.Optional;

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

            entityRecordList.forEach(vEntityRecord -> entityList.add(extractEntity(vEntityRecord)));

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return entityList;
    }

    @Override
    public List<NaturalAISEntity> findCompanyRepresentatives(int companyId) {

        final List<NaturalAISEntity> entityList = new ArrayList<>();

        try (Connection connection = DBCPDataSource.getConnection()) {

            DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);

            List<EntityRecord> entityRecordList = context
                .selectFrom(Entity.ENTITY)
                .where(Entity.ENTITY.COMPANY_ID.eq(companyId))
                .fetch();

            entityRecordList.forEach(entityRecord -> entityList.add(extractNaturalEntity(entityRecord)));

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return entityList;
    }

    /**
     * Find entity by id.
     *
     * @param id     The entity id
     * @param userId The user id
     * @return The entity
     */
    @Override
    public Optional<AISEntity> findEntityById(int id, int userId) {

        AISEntity entity = null;

        try (Connection connection = DBCPDataSource.getConnection()) {

            DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);

            // Result<Record> records =
            Map<EntityRecord, Result<BankAccountRecord>> records =
                context.select().from(Entity.ENTITY.leftJoin(BankAccount.BANK_ACCOUNT)
                    .on(Entity.ENTITY.ENTITY_ID.eq(BankAccount.BANK_ACCOUNT.ENTITY_ID)))
                    .where(Entity.ENTITY.ENTITY_ID.eq(id))
                    .and(Entity.ENTITY.USER_ID.eq(userId))
                    // .fetch();
                    .fetchGroups(Entity.ENTITY, BankAccount.BANK_ACCOUNT);

            entity = extractEntity(records);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return Optional.ofNullable(entity);
    }

}
