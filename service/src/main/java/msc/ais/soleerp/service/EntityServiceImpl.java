package msc.ais.soleerp.service;

import msc.ais.soleerp.db.DaoFactory;
import msc.ais.soleerp.model.AISEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 21/2/21.
 */
public class EntityServiceImpl implements EntityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityServiceImpl.class);

    @Override
    public List<AISEntity> findEntitiesByTokenId(String tokenId) {
        return DaoFactory.createEntityDao().findEntities(tokenId);
    }

    @Override
    public Optional<AISEntity> findEntityById(int id, String tokenId) {
        int userId = DaoFactory.createUserDao().findUserIdByTokenId(tokenId);
        LOGGER.info("Token: " + tokenId + " belongs to user with userId: " + userId);
        return DaoFactory.createEntityDao().findEntityById(id, userId);
    }
}
