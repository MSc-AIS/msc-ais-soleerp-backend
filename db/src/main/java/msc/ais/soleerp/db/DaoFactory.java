package msc.ais.soleerp.db;

import msc.ais.soleerp.db.postgres.PostgresEntityDao;
import msc.ais.soleerp.db.postgres.PostgresReferenceCodesDao;
import msc.ais.soleerp.db.postgres.PostgresTokenDao;
import msc.ais.soleerp.db.postgres.PostgresUserDao;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 19/2/2021.
 */
public class DaoFactory {

    public static UserDao createUserDao() {
        return new PostgresUserDao();
    }

    public static TokenDao createTokenDao() {
        return new PostgresTokenDao();
    }

    public static EntityDao createEntityDao() {
        return new PostgresEntityDao();
    }

    public static ReferenceCodesDao createReferenceCodesDao() {
        return new PostgresReferenceCodesDao();
    }

}
