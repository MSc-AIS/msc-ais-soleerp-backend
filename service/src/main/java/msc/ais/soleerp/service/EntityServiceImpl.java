package msc.ais.soleerp.service;

import msc.ais.soleerp.db.DaoFactory;
import msc.ais.soleerp.db.jooq.generated.tables.records.EntityRecord;
import msc.ais.soleerp.model.AISEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.NoSuchElementException;
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
        return DaoFactory.createEntityDao().findEntityById(id, getUserByTokenId(tokenId));
    }

    @Override
    public boolean deleteEntityById(int id, String tokenId) {
        return DaoFactory.createEntityDao().deleteEntityById(id, getUserByTokenId(tokenId)) > 0;
    }

    @Override
    public boolean updateEntityById(int id, String tokenId, AISEntity entity) {
        return DaoFactory.createEntityDao().updateEntityById(id, getUserByTokenId(tokenId), entity) > 0;
    }

    @Override
    public int updateEntityById(int id, String tokenId, EntityRecord record) {
        return DaoFactory.createEntityDao().updateEntityById(id, getUserByTokenId(tokenId), record);
    }

    @Override
    public int insertEntity(String tokenId, EntityRecord record) {
        return DaoFactory.createEntityDao().insertEntity(getUserByTokenId(tokenId), record);
    }

    private int getUserByTokenId(String tokenId) {
        int userId = DaoFactory.createUserDao().findUserIdByTokenId(tokenId);

        if (userId == -1) {
            throw new NoSuchElementException("Error... Unable to find a user for token: " + tokenId);
        }

        LOGGER.info("Token: " + tokenId + " belongs to user with userId: " + userId);
        return userId;
    }

}
