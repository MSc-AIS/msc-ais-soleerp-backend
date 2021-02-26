package msc.ais.soleerp.service;

import msc.ais.soleerp.db.DaoFactory;
import msc.ais.soleerp.db.jooq.generated.tables.records.EntityRecord;
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
    private final UserService userService;

    public EntityServiceImpl() {
        this.userService = ServiceFactory.createUserService();
    }

    @Override
    public List<AISEntity> findEntitiesByTokenId(String tokenId) {
        return DaoFactory.createEntityDao().findEntities(tokenId);
    }

    @Override
    public Optional<AISEntity> findEntityById(int id, String tokenId) {
        return DaoFactory.createEntityDao().findEntityById(id, userService.getUserByTokenId(tokenId));
    }

    @Override
    public boolean deleteEntityById(int id, String tokenId) {
        return DaoFactory.createEntityDao().deleteEntityById(id, userService.getUserByTokenId(tokenId)) > 0;
    }

    @Override
    public boolean updateEntityById(int id, String tokenId, AISEntity entity) {
        return DaoFactory.createEntityDao().updateEntityById(id, userService.getUserByTokenId(tokenId), entity) > 0;
    }

    @Override
    public int updateEntityById(int id, String tokenId, EntityRecord record) {
        return DaoFactory.createEntityDao().updateEntityById(id, userService.getUserByTokenId(tokenId), record);
    }

    @Override
    public int insertEntity(String tokenId, EntityRecord record) {
        return DaoFactory.createEntityDao().insertEntity(userService.getUserByTokenId(tokenId), record);
    }

}
