package msc.ais.soleerp.service;

import msc.ais.soleerp.db.DaoFactory;
import msc.ais.soleerp.db.EntityDao;
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
    private final EntityDao entityDao;

    public EntityServiceImpl() {
        this.userService = ServiceFactory.createUserService();
        this.entityDao = DaoFactory.createEntityDao();
    }

    @Override
    public List<AISEntity> findEntitiesByTokenId(String tokenId) {
        return entityDao.findEntities(tokenId);
    }

    @Override
    public Optional<AISEntity> findEntityById(int id, String tokenId) {
        return entityDao.findEntityById(id, userService.getUserByTokenId(tokenId));
    }

    @Override
    public boolean deleteEntityById(int id, String tokenId) {
        return entityDao.deleteEntityById(id, userService.getUserByTokenId(tokenId)) > 0;
    }

    @Override
    public boolean updateEntityById(int id, String tokenId, AISEntity entity) {
        return entityDao.updateEntityById(id, userService.getUserByTokenId(tokenId), entity) > 0;
    }

    @Override
    public int updateEntityById(int id, String tokenId, EntityRecord record) {
        return entityDao.updateEntityById(id, userService.getUserByTokenId(tokenId), record);
    }

    @Override
    public int insertEntity(String tokenId, EntityRecord record) {
        return entityDao.insertEntity(userService.getUserByTokenId(tokenId), record);
    }

    @Override
    public boolean isEntityBelongToUser(int id, String tokenId) {
        return entityDao.isEntityBelongToUser(id, userService.getUserByTokenId(tokenId));
    }
}
