package msc.ais.soleerp.db.postgres;

import msc.ais.soleerp.db.UserDao;
import msc.ais.soleerp.model.User;

import java.util.Optional;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 19/2/21.
 */
public class PostgresUserDao implements UserDao {

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
        return Optional.empty();
    }

}
