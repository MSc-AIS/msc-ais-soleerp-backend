package msc.ais.soleerp.service;

import msc.ais.soleerp.model.AISEntity;

import java.util.List;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 21/2/21.
 */
public interface EntityService {

    List<AISEntity> findEntitiesByTokenId(String tokenId);

}
