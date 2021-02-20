package msc.ais.soleerp.service;

import msc.ais.soleerp.db.DaoFactory;
import msc.ais.soleerp.db.TokenDao;
import msc.ais.soleerp.db.UserDao;
import msc.ais.soleerp.db.exception.DataException;
import msc.ais.soleerp.db.util.StoreResult;
import msc.ais.soleerp.model.AISToken;
import msc.ais.soleerp.model.AISUser;
import msc.ais.soleerp.model.response.AISUserResponse;
import msc.ais.soleerp.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 19/2/2021.
 */
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * Sign up a user tne return a token.
     *
     * @param user The user
     * @return The user response
     */
    @Override
    public Optional<AISUserResponse> signUp(AISUser user) {

        LOGGER.info("Trying to SignUp user: " + user.getEmail());

        UserDao userDao = DaoFactory.createUserDao();
        AISUserResponse response = null;

        try {

            AISUser aisUserResult = userDao.insertUser(user)
                .orElseThrow(() -> new ServiceException(
                    "Error... User: " + user.getEmail() + " failed to be inserted in db."));

            LOGGER.info("User: " + user.getEmail() + " inserted successfully.");
            TokenDao tokenDao = DaoFactory.createTokenDao();
            AISToken token = AISToken.builder()
                .userId(aisUserResult.getId())
                .tokenId(UUID.randomUUID().toString())
                .build();

            StoreResult tokenStoreResult = tokenDao.insertToken(token);
            LOGGER.info("Token " + token.getId() + " store result is: " + tokenStoreResult);
            if (tokenStoreResult == StoreResult.SUCCESS) {
                response = new AISUserResponse(aisUserResult, token.getId());
            }

        } catch (ServiceException | DataException e) {
            LOGGER.error(e.getMessage());
        }

        return Optional.ofNullable(response);
    }

    /**
     * Sign in a user by credentials.
     *
     * @param username The username
     * @param password The password
     * @return The user response
     */
    @Override
    public Optional<AISUserResponse> singIn(String username, char[] password) {

        LOGGER.debug("Trying to Sign in user with username: " + username);
        AISUserResponse response = null;

        try {

            UserDao userDao = DaoFactory.createUserDao();
            AISUser user = userDao.findUserByCredentials(username, password)
                .orElseThrow(() -> new ServiceException(
                    "Error... Cannot find user with username: "
                        + username + " and password: " + String.valueOf(password)));

            TokenDao tokenDao = DaoFactory.createTokenDao();
            AISToken token = AISToken.builder()
                .userId(user.getId())
                .tokenId(UUID.randomUUID().toString())
                .build();
            StoreResult tokenStoreResult = tokenDao.insertToken(token);
            LOGGER.info("Token " + token.getId() + " store result is: " + tokenStoreResult);
            if (tokenStoreResult == StoreResult.SUCCESS) {
                response = new AISUserResponse(user, token.getId());
            }

        } catch (ServiceException | DataException e) {
            LOGGER.error(e.getMessage());
        }

        return Optional.ofNullable(response);
    }

    /**
     * Sign out a user by its token.
     *
     * @param tokenId The token UUID
     * @return True for successful deletion, false otherwise
     */
    @Override
    public boolean signOut(String tokenId) {

        TokenDao tokenDao = DaoFactory.createTokenDao();
        boolean isDeleted = false;
        int rowsAffected = tokenDao.deleteTokenById(tokenId);
        if (rowsAffected == 1) {
            isDeleted = true;
            LOGGER.debug("Token with id: " + tokenId + " deleted successfully.");
        }

        LOGGER.debug("Trying to delete token with id: " + tokenId);

        return isDeleted;
    }
}
