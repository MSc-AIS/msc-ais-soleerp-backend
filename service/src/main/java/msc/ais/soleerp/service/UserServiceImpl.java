package msc.ais.soleerp.service;

import msc.ais.soleerp.db.DaoFactory;
import msc.ais.soleerp.db.UserDao;
import msc.ais.soleerp.db.util.StoreResult;
import msc.ais.soleerp.model.AISUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

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
        StoreResult storeResult =userDao.insertUser(user);

        switch (storeResult) {

            case SUCCESS:
                LOGGER.info("User: " + user.getEmail() + " inserted successfully!!!");

        }

        return Optional.empty();
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
