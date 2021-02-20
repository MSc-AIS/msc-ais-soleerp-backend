package msc.ais.soleerp.service;

import msc.ais.soleerp.db.DaoFactory;
import msc.ais.soleerp.db.TokenDao;
import msc.ais.soleerp.db.UserDao;
import msc.ais.soleerp.db.util.StoreMetadata;
import msc.ais.soleerp.db.util.StoreResult;
import msc.ais.soleerp.model.AISToken;
import msc.ais.soleerp.model.AISUser;
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
    public Optional<String> signUp(AISUser user) {

        LOGGER.info("Trying to SignUp user: " + user.getEmail());

        UserDao userDao = DaoFactory.createUserDao();
        StoreMetadata userStoreMetadata = userDao.insertUser(user);
        String tokenId = null;

        switch (userStoreMetadata.getStoreResult()) {

            case SUCCESS:

                LOGGER.info("User: " + user.getEmail() + " inserted successfully!!!");
                TokenDao tokenDao = DaoFactory.createTokenDao();
                String tempTokenId = UUID.randomUUID().toString();
                AISToken token = AISToken.builder()
                    .userId(userStoreMetadata.getAutoGenId())
                    .tokenId(tempTokenId)
                    .build();

                StoreResult tokenStoreResult = tokenDao.insertToken(token);
                // set tokenId only if the token inserted successfully in db
                if (tokenStoreResult == StoreResult.SUCCESS) {
                    tokenId = tempTokenId;
                }
                LOGGER.info("Token " + tempTokenId + " store result is: " + tokenStoreResult);
                break;

            case UNNECESSARY:

                LOGGER.info("User: " + user.getEmail() + " already stored...");
                break;

            default:

                LOGGER.info("User: " + user.getEmail() + " failed to be inserted...");

        }

        return Optional.ofNullable(tokenId);
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
