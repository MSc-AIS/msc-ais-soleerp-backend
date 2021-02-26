package msc.ais.soleerp.service;

import msc.ais.soleerp.db.DaoFactory;
import msc.ais.soleerp.model.AISItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 26/2/2021.
 */
public class ItemServiceImpl implements ItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);
    private final UserService userService;

    public ItemServiceImpl() {
        this.userService = ServiceFactory.createUserService();
    }

    @Override
    public int insertItem(String tokenId, AISItem item) {
        return DaoFactory.createItemDao().insertItem(userService.getUserByTokenId(tokenId), item);
    }
}
