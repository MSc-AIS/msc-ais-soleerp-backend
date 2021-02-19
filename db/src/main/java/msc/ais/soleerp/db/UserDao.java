package msc.ais.soleerp.db;

import msc.ais.soleerp.model.User;

import java.util.Optional;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 19/2/21.
 */
public interface UserDao {

    int insertUser(User user);

    int deleteUserById(int id);

    int findUserById(int id);

    Optional<User> findUserByCredentials(String email, char[] password);
}
