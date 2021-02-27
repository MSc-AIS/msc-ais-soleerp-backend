package msc.ais.soleerp.db.postgres;

import msc.ais.soleerp.db.DBCPDataSource;
import msc.ais.soleerp.db.UserDao;
import msc.ais.soleerp.db.exception.DataException;
import msc.ais.soleerp.db.jooq.generated.tables.AppUser;
import msc.ais.soleerp.db.jooq.generated.tables.Token;
import msc.ais.soleerp.db.util.ModelExtractor;
import msc.ais.soleerp.model.AISUser;
import org.jooq.*;
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
public class PostgresUserDao implements UserDao, ModelExtractor {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgresUserDao.class);

    @Override
    public Optional<AISUser> insertUser(AISUser user) {

        AISUser aisUserResult = null;

        try (Connection connection = DBCPDataSource.getConnection()) {

            DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);

            Record2<Integer, LocalDate> record2Result = context.insertInto(AppUser.APP_USER,
                AppUser.APP_USER.USERNAME,
                AppUser.APP_USER.EMAIL,
                AppUser.APP_USER.FIRST_NAME,
                AppUser.APP_USER.LAST_NAME,
                AppUser.APP_USER.PASSWORD)
                .values(user.getUsername(),
                    user.getEmail(),
                    user.getFirstName(),
                    user.getLastName(),
                    String.valueOf(user.getPassword()))
                .returningResult(AppUser.APP_USER.USER_ID, AppUser.APP_USER.DATE_CREATED)
                .fetchOne();

            aisUserResult = AISUser.builder()
                .userId(Objects.requireNonNull(record2Result).getValue(AppUser.APP_USER.USER_ID))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .createdDate(Objects.requireNonNull(record2Result).getValue(AppUser.APP_USER.DATE_CREATED, LocalDate.class))
                .build();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return Optional.ofNullable(aisUserResult);
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
    public int findUserIdByTokenId(String tokenId) {

        int userId = -1;

        try (Connection connection = DBCPDataSource.getConnection()) {

            DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);

            Result<Record1<Integer>> record1Result = context
                .select(Token.TOKEN.USER_ID)
                .from(Token.TOKEN)
                .where(Token.TOKEN.TOKEN_.eq(tokenId))
                .fetch();

            // need at least 1 token in db
            if (!record1Result.isEmpty()) {
                userId = record1Result.getValue(0, Token.TOKEN.USER_ID);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return userId;
    }

    @Override
    public Optional<AISUser> findUserByCredentials(String username, char[] password) {

        AISUser user = null;

        try (Connection connection = DBCPDataSource.getConnection()) {

            DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);

            Record appUserRecord = context
                .selectFrom(AppUser.APP_USER)
                .where(AppUser.APP_USER.USERNAME.eq(username))
                .and(AppUser.APP_USER.PASSWORD.eq(String.valueOf(password)))
                .fetchOptional()
                .orElseThrow(() -> new DataException("Error... Unable to find user for given credentials."));

            user = AISUser.builder()
                .userId(Objects.requireNonNull(appUserRecord).getValue(AppUser.APP_USER.USER_ID))
                .email(Objects.requireNonNull(appUserRecord).getValue(AppUser.APP_USER.EMAIL))
                .createdDate(Objects.requireNonNull(appUserRecord).getValue(AppUser.APP_USER.DATE_CREATED, LocalDate.class))
                // .password(Objects.requireNonNull(appUserRecord).getValue(AppUser.APP_USER.PASSWORD).toCharArray())
                .firstName(Objects.requireNonNull(appUserRecord).getValue(AppUser.APP_USER.FIRST_NAME))
                .lastName(Objects.requireNonNull(appUserRecord).getValue(AppUser.APP_USER.LAST_NAME))
                // .username(Objects.requireNonNull(appUserRecord).getValue(AppUser.APP_USER.USERNAME))
                .build();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (DataException e) {
            LOGGER.error(e.getMessage());
        }

        return Optional.ofNullable(user);
    }

}
