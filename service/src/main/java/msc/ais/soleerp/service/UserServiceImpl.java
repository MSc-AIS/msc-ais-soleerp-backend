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
     * @return The token
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

    @Override
    public Optional<String> singIn(String email, char[] password) {
        return Optional.empty();
    }

    @Override
    public boolean signOut(String tokenId) {
        return false;
    }
}
