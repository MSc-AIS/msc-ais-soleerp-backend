package msc.ais.soleerp.service;

import msc.ais.soleerp.model.AISItem;

import java.util.List;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 26/2/2021.
 */
public interface ItemService {

    int insertItem(String tokenId, AISItem item);

    List<AISItem> findItems(String tokenId);

    int deleteItemById(int id, String tokenId);

    int updatedItemById(int id, String tokenId, AISItem item);
}
