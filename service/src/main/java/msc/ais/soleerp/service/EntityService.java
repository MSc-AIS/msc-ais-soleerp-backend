package msc.ais.soleerp.service;

import msc.ais.soleerp.db.jooq.generated.tables.records.EntityRecord;
import msc.ais.soleerp.model.AISEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 21/2/21.
 */
public interface EntityService {

    List<AISEntity> findEntitiesByTokenId(String tokenId);

    Optional<AISEntity> findEntityById(int id, String tokenId);

    boolean deleteEntityById(int id, String tokenId);

    boolean updateEntityById(int id, String tokenId, AISEntity entity);

    int updateEntityById(int id, String tokenId, EntityRecord record);

    int insertEntity(String tokenId, EntityRecord record);

    boolean isEntityBelongToUser(int id, String tokenId);
}
