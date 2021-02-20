package msc.ais.soleerp.db.postgres;

import msc.ais.soleerp.db.DaoFactory;
import msc.ais.soleerp.db.TokenDao;
import msc.ais.soleerp.db.util.StoreResult;
import msc.ais.soleerp.model.AISToken;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 20/2/2021.
 */
public class TokenDaoTest {

    @Test
    public void insertToken() {

        TokenDao tokenDao = DaoFactory.createTokenDao();
        AISToken token = AISToken.builder()
            .tokenId(UUID.randomUUID().toString())
            .userId(18)
            .build();

        StoreResult storeResult = tokenDao.insertToken(token);
        System.out.println("The store result is: " + storeResult);
    }

}
