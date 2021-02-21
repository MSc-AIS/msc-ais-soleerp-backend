package msc.ais.soleerp.service;

import msc.ais.soleerp.db.DaoFactory;
import msc.ais.soleerp.model.AISEntity;

import java.util.List;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 21/2/21.
 */
public class EntityServiceImpl implements EntityService {

    @Override
    public List<AISEntity> findEntitiesByTokenId(String tokenId) {
        return DaoFactory.createEntityDao().findEntities(tokenId);
    }
}
