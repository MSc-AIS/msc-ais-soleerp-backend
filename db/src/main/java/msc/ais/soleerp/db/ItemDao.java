package msc.ais.soleerp.db;

import msc.ais.soleerp.model.AISItem;

import java.util.List;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 26/2/2021.
 */
public interface ItemDao {

    int insertItem(int userId, AISItem item);

    int updateItemById(int id, int userId, AISItem item);

    List<AISItem> findItems(int userId);

    int deleteItemById(int id, int userId);

}
