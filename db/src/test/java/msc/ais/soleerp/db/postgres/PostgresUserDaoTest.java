package msc.ais.soleerp.db.postgres;

import msc.ais.soleerp.db.DaoFactory;
import msc.ais.soleerp.db.UserDao;
import msc.ais.soleerp.db.util.StoreMetadata;
import msc.ais.soleerp.db.util.StoreResult;
import msc.ais.soleerp.model.AISUser;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 19/2/2021.
 */
public class PostgresUserDaoTest {

    @Disabled
    @Test
    public void testInsertUser() {

        UserDao userDao = DaoFactory.createUserDao();
        AISUser user = AISUser.builder()
            .username("KRtester3")
            .password(new char[] {'a', 'b', 'c'})
            .email("KRtester3@test.com")
            .build();

        StoreMetadata storeMetadata = userDao.insertUser(user);
        System.out.println("The store result is: " + storeMetadata.getStoreResult());
    }

    @Disabled
    @Test
    public void testFindUserByCredentials() {

        UserDao userDao = DaoFactory.createUserDao();
        AISUser user = userDao.findUserByCredentials("rduesberryb@163.com",
            new char[] {'b', 'O', '3', 'i', 'z', '8', '5', 'f', '9', 'V', 'a'})
            .orElseThrow(() -> new NoSuchElementException("Error... Empty optional!"));

        System.out.println(user.toString());
    }

}
