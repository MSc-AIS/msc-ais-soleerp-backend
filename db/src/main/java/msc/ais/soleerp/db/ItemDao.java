package msc.ais.soleerp.db;

import msc.ais.soleerp.model.AISItem;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 26/2/2021.
 */
public interface ItemDao {

    int insertItem(int userId, AISItem item);



}
