package msc.ais.soleerp.db.postgres;

import msc.ais.soleerp.db.DaoFactory;
import msc.ais.soleerp.db.UserDao;
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

        int number = 7;

        UserDao userDao = DaoFactory.createUserDao();
        AISUser user = AISUser.builder()
            .username("KRtester" + number)
            .password(new char[] {'a', 'b', 'c'})
            .firstName("KRtester" + number)
            .lastName("KRtester" + number)
            .email("KRteste" + number + "@test.com")
            .build();

        AISUser aisUserResult = userDao.insertUser(user)
            .orElseThrow(() -> new NoSuchElementException("Error... Empty optional!"));

        System.out.println("The user result is: " + aisUserResult.toString());
    }

    @Disabled
    @Test
    public void testFindUserByCredentials() {

        UserDao userDao = DaoFactory.createUserDao();
//        AISUser user = userDao.findUserByCredentials("rduesberryb@163.com",
//            new char[] {'b', 'O', '3', 'i', 'z', '8', '5', 'f', '9', 'V', 'a'})
//            .orElseThrow(() -> new NoSuchElementException("Error... Empty optional!"));

        AISUser user = userDao.findUserByCredentials("KRtester6",
            new char[] {'a', 'b', 'c'})
            .orElseThrow(() -> new NoSuchElementException("Error... Empty optional!"));

        System.out.println(user.toString());
    }

}
