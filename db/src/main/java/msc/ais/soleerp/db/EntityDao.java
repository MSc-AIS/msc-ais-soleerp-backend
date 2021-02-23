package msc.ais.soleerp.db;

import msc.ais.soleerp.model.AISEntity;
import msc.ais.soleerp.model.NaturalAISEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 21/2/21.
 */
public interface EntityDao {

    List<AISEntity> findEntities(String tokenId);

    Optional<AISEntity> findEntityById(int id, int userId);

    List<NaturalAISEntity> findCompanyRepresentatives(int companyId);

}
