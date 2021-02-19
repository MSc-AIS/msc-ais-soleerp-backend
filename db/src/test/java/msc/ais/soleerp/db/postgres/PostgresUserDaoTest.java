package msc.ais.soleerp.db.postgres;

import msc.ais.soleerp.db.DaoFactory;
import msc.ais.soleerp.db.UserDao;
import msc.ais.soleerp.model.User;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 19/2/2021.
 */
public class PostgresUserDaoTest {

    @Test
    public void testFindUserByCredentials() throws Exception {

        UserDao userDao = DaoFactory.createUserDao();
        User user = userDao.findUserByCredentials("rduesberryb@163.com",
            new char[] {'b', 'O', '3', 'i', 'z', '8', '5', 'f', '9', 'V', 'a'})
            .orElseThrow(() -> new NoSuchElementException("Error... Empty optional!"));

        // bO3iz85f9Va

        System.out.println(user.toString());
    }

}
