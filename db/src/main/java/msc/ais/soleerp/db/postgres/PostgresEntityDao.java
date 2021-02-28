package msc.ais.soleerp.db.postgres;

import msc.ais.soleerp.db.DBCPDataSource;
import msc.ais.soleerp.db.EntityDao;
import msc.ais.soleerp.db.jooq.generated.tables.BankAccount;
import msc.ais.soleerp.db.jooq.generated.tables.Entity;
import msc.ais.soleerp.db.jooq.generated.tables.ReferenceCodes;
import msc.ais.soleerp.db.jooq.generated.tables.VEntity;
import msc.ais.soleerp.db.jooq.generated.tables.records.EntityRecord;
import msc.ais.soleerp.db.jooq.generated.tables.records.VEntityRecord;
import msc.ais.soleerp.db.util.ModelExtractor;
import msc.ais.soleerp.model.AISEntity;
import msc.ais.soleerp.model.NaturalAISEntity;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 21/2/21.
 */
public class PostgresEntityDao implements EntityDao, ModelExtractor {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgresEntityDao.class);

    @Override
    public int insertEntity(int userId, EntityRecord record) {

        int entityId = -1;

        try (Connection connection = DBCPDataSource.getConnection()) {

            DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);
            record.setUserId(userId);
            Entity ent = Entity.ENTITY;

            EntityRecord insertedEntity = context.insertInto(ent)
                .set(record)
                .returning(Entity.ENTITY.ENTITY_ID)
                .fetchOne();

            if (!Objects.isNull(insertedEntity)) {
                entityId = insertedEntity.getEntityId();
                LOGGER.info("Created entity id: " + entityId);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return entityId;
    }

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

            // alias
            BankAccount ba = BankAccount.BANK_ACCOUNT.as("ba");
            Entity en = Entity.ENTITY.as("en");
            ReferenceCodes rc1 = ReferenceCodes.REFERENCE_CODES.as("rc1");
            ReferenceCodes rc2 = ReferenceCodes.REFERENCE_CODES.as("rc2");

            Result<Record> records =
                context.select()
                    .from(en.leftJoin(ba)
                        .on(en.ENTITY_ID.eq(ba.ENTITY_ID))
                        .leftJoin(rc1)
                        .on(ba.BANK_NAME_CODE.eq(rc1.REF_VALUE).and(rc1.REF_DOMAIN.eq("bank_name_code")))
                        .join(rc2)
                        .on(en.TAX_OFFICE_CODE.eq(rc2.REF_VALUE).and(rc2.REF_DOMAIN.eq("tax_office_code"))))
                    .where(en.ENTITY_ID.eq(id))
                    .and(en.USER_ID.eq(userId))
                    .fetch();

            entity = extractEntity(records);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (NoSuchElementException e) {
            LOGGER.info(e.getMessage());
        }

        return Optional.ofNullable(entity);
    }

    @Override
    public int deleteEntityById(int id, int userId) {

        int rowsDeleted = -1;

        try (Connection connection = DBCPDataSource.getConnection()) {

            DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);

            rowsDeleted = context
                .deleteFrom(Entity.ENTITY)
                .where(Entity.ENTITY.ENTITY_ID.eq(id))
                .and(Entity.ENTITY.USER_ID.eq(userId))
                .execute();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return rowsDeleted;
    }

    @Override
    public int updateEntityById(int id, int userId, AISEntity entity) {

        int rowsUpdated = -1;

        try (Connection connection = DBCPDataSource.getConnection()) {

            DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);
            Entity ent = Entity.ENTITY;

            rowsUpdated = context.update(ent)
                .set(extractEntityRecord(entity))
                .where(ent.ENTITY_ID.eq(id))
                .and(ent.USER_ID.eq(userId))
                .execute();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IllegalStateException e) {
            LOGGER.info(e.getMessage());
        }

        return rowsUpdated;
    }

    @Override
    public int updateEntityById(int id, int userId, EntityRecord record) {

        int rowsUpdated = -1;

        try (Connection connection = DBCPDataSource.getConnection()) {

            DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);
            record.setUserId(userId);
            Entity ent = Entity.ENTITY;

            rowsUpdated = context.update(ent)
                .set(record)
                .where(ent.ENTITY_ID.eq(id))
                .and(ent.USER_ID.eq(userId))
                .execute();

            LOGGER.info("Rows Updated: " + rowsUpdated);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return rowsUpdated;
    }

    @Override
    public boolean isEntityBelongToUser(int id, int userId) {

        boolean result = false;

        try (Connection connection = DBCPDataSource.getConnection()) {

            DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);
            Entity e = Entity.ENTITY;

            Record1<Integer> record = context.select(e.ENTITY_ID)
                .from(e)
                .where(e.ENTITY_ID.eq(id))
                .and(e.USER_ID.eq(userId))
                .fetchOne();

            if (!Objects.isNull(record)) {
                result = true;
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return result;
    }
}
