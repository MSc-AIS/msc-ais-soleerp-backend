package msc.ais.soleerp.db;

import msc.ais.soleerp.db.exception.DataException;
import msc.ais.soleerp.db.util.StoreResult;
import msc.ais.soleerp.model.AISToken;

import javax.xml.crypto.Data;
import java.util.Optional;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 20/2/2021.
 */
public interface TokenDao {

    StoreResult insertToken(AISToken token) throws DataException;

    Optional<AISToken> findTokenById(String id);

    Optional<AISToken> findTokenByUserCredentials(String email, char[] password);

    int deleteTokenById(String id);

}
