package msc.ais.soleerp.service;

import msc.ais.soleerp.db.DaoFactory;
import msc.ais.soleerp.db.ItemDao;
import msc.ais.soleerp.model.AISItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 26/2/2021.
 */
public class ItemServiceImpl implements ItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);
    private final UserService userService;
    private final ItemDao itemDao;

    public ItemServiceImpl() {
        this.userService = ServiceFactory.createUserService();
        this.itemDao = DaoFactory.createItemDao();
    }

    @Override
    public int insertItem(String tokenId, AISItem item) {
        return itemDao.insertItem(userService.getUserByTokenId(tokenId), item);
    }

    @Override
    public List<AISItem> findItems(String tokenId) {
        return itemDao.findItems(userService.getUserByTokenId(tokenId));
    }

    @Override
    public int deleteItemById(int id, String tokenId) {
        return itemDao.deleteItemById(id, userService.getUserByTokenId(tokenId));
    }

    @Override
    public int updatedItemById(int id, String tokenId, AISItem item) {
        return itemDao.updateItemById(id, userService.getUserByTokenId(tokenId), item);
    }
}
