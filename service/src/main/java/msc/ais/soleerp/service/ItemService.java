package msc.ais.soleerp.service;

import msc.ais.soleerp.model.AISItem;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 26/2/2021.
 */
public interface ItemService {

    int insertItem(String tokenId, AISItem item);

}
