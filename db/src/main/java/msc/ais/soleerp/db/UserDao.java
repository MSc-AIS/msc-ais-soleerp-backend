package msc.ais.soleerp.db;

import msc.ais.soleerp.model.AISUser;

import java.util.Optional;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 19/2/21.
 */
public interface UserDao {

    Optional<AISUser> insertUser(AISUser user);

    int deleteUserById(int id);

    int findUserById(int id);

    int findUserIdByTokenId(String tokenId);

    Optional<AISUser> findUserByCredentials(String username, char[] password);
}
