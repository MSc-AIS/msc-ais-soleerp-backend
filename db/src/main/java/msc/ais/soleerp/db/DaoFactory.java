package msc.ais.soleerp.db;

import msc.ais.soleerp.db.postgres.PostgresUserDao;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 19/2/2021.
 */
public class DaoFactory {

    public static UserDao createUserDao() {
        return new PostgresUserDao();
    }

}
