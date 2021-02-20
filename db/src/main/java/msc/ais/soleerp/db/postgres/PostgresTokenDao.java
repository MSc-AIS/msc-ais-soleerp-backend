package msc.ais.soleerp.db.postgres;

import msc.ais.soleerp.db.DBCPDataSource;
import msc.ais.soleerp.db.TokenDao;
import msc.ais.soleerp.db.exception.DataException;
import msc.ais.soleerp.db.jooq.generated.tables.Token;
import msc.ais.soleerp.db.jooq.generated.tables.records.TokenRecord;
import msc.ais.soleerp.db.util.StoreResult;
import msc.ais.soleerp.db.util.StoreResultExtractor;
import msc.ais.soleerp.model.AISToken;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 20/2/2021.
 */
public class PostgresTokenDao implements TokenDao, StoreResultExtractor {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgresTokenDao.class);

    @Override
    public StoreResult insertToken(AISToken token) throws DataException {

        int storeResult = -1;

        try (Connection connection = DBCPDataSource.getConnection()) {

            DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);

            TokenRecord tokenRecord = context.newRecord(Token.TOKEN);
            tokenRecord.setToken(token.getId());
            tokenRecord.setUserId(token.getUserId());
            storeResult = tokenRecord.store();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (DataAccessException e) {
            throw new DataException(e);
        }

        return extractStoreResult(storeResult);
    }

    @Override
    public Optional<AISToken> findTokenById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<AISToken> findTokenByUserCredentials(String email, char[] password) {
        return Optional.empty();
    }

    @Override
    public int deleteTokenById(String id) {

        int deleteResult = -1;

        try (Connection connection = DBCPDataSource.getConnection()) {

            DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);

            deleteResult = context.delete(Token.TOKEN)
                .where(Token.TOKEN.TOKEN_.eq(id))
                .execute();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return deleteResult;
    }
}
