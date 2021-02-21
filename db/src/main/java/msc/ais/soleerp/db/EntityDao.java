package msc.ais.soleerp.db;

import msc.ais.soleerp.model.AISEntity;

import java.util.List;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 21/2/21.
 */
public interface EntityDao {

    List<AISEntity> findEntities(String tokenId);

}
