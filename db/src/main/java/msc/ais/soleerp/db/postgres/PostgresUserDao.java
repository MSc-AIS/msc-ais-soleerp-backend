package msc.ais.soleerp.db.postgres;

import msc.ais.soleerp.db.DBCPDataSource;
import msc.ais.soleerp.db.UserDao;
import msc.ais.soleerp.db.jooq.generated.tables.AppUser;
import msc.ais.soleerp.model.User;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 19/2/21.
 */
public class PostgresUserDao implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgresUserDao.class);

    @Override
    public int insertUser(User user) {
        return 0;
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
    public Optional<User> findUserByCredentials(String email, char[] password) {

        User user = null;

        try (Connection connection = DBCPDataSource.getConnection()) {

            DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);

            Record AppUserRecord = context
                .selectFrom("app_user")
                .where(AppUser.APP_USER.EMAIL.eq(email))
                .and(AppUser.APP_USER.PASSWORD.eq(String.valueOf(password)))
                .fetchOne();

            user = User.builder()
                .userId(Objects.requireNonNull(AppUserRecord).getValue(AppUser.APP_USER.USER_ID))
                .email(Objects.requireNonNull(AppUserRecord).getValue(AppUser.APP_USER.EMAIL))
                // .createdDate(Objects.requireNonNull(AppUserRecord).getValue(AppUser.APP_USER.DATE_CREATED))
                .build();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return Optional.ofNullable(user);
    }

}
