package msc.ais.soleerp.db.postgres;

import msc.ais.soleerp.db.DBCPDataSource;
import msc.ais.soleerp.db.UserDao;
import msc.ais.soleerp.db.jooq.generated.tables.AppUser;
import msc.ais.soleerp.db.jooq.generated.tables.records.AppUserRecord;
import msc.ais.soleerp.db.util.StoreMetadata;
import msc.ais.soleerp.db.util.StoreResult;
import msc.ais.soleerp.db.util.StoreResultExtractor;
import msc.ais.soleerp.model.AISUser;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 19/2/21.
 */
public class PostgresUserDao implements UserDao, StoreResultExtractor {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgresUserDao.class);

    @Override
    public StoreMetadata insertUser(AISUser user) {

        int storeResult = -1;
        int autoGenId = -1;

        try (Connection connection = DBCPDataSource.getConnection()) {

            DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);

            AppUserRecord appUserRecord = context.newRecord(AppUser.APP_USER);
            appUserRecord.setUsername(user.getUsername());
            appUserRecord.setEmail(user.getEmail());
            appUserRecord.setPassword(String.valueOf(user.getPassword()));
            storeResult = appUserRecord.store();
            autoGenId = appUserRecord.getUserId();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return extractStoreMetadata(storeResult, autoGenId);
    }

    @Override
    public int deleteUserById(int id) {
        return 0;
    }

    @Override
    public int findUserById(int id) {
        return 0;
    }

    @Override
    public Optional<AISUser> findUserByCredentials(String email, char[] password) {

        AISUser user = null;

        try (Connection connection = DBCPDataSource.getConnection()) {

            DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);

            Record AppUserRecord = context
                .selectFrom("app_user")
                .where(AppUser.APP_USER.EMAIL.eq(email))
                .and(AppUser.APP_USER.PASSWORD.eq(String.valueOf(password)))
                .fetchOne();

            user = AISUser.builder()
                .userId(Objects.requireNonNull(AppUserRecord).getValue(AppUser.APP_USER.USER_ID))
                .email(Objects.requireNonNull(AppUserRecord).getValue(AppUser.APP_USER.EMAIL))
                .createdDate(Objects.requireNonNull(AppUserRecord).getValue(AppUser.APP_USER.DATE_CREATED, LocalDate.class))
                .password(Objects.requireNonNull(AppUserRecord).getValue(AppUser.APP_USER.PASSWORD).toCharArray())
                .username(Objects.requireNonNull(AppUserRecord).getValue(AppUser.APP_USER.USERNAME))
                .build();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return Optional.ofNullable(user);
    }

}
