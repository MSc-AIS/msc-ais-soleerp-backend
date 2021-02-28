package msc.ais.soleerp.db;

import msc.ais.soleerp.db.jooq.generated.tables.records.EntityRecord;
import msc.ais.soleerp.model.AISEntity;
import msc.ais.soleerp.model.NaturalAISEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 21/2/21.
 */
public interface EntityDao {

    int insertEntity(int userId, EntityRecord record);

    List<AISEntity> findEntities(String tokenId);

    Optional<AISEntity> findEntityById(int id, int userId);

    boolean isEntityBelongToUser(int id, int userId);

    int deleteEntityById(int id, int userId);

    List<NaturalAISEntity> findCompanyRepresentatives(int companyId);

    int updateEntityById(int id, int userId, AISEntity entity);

    int updateEntityById(int id, int userId, EntityRecord record);
}
