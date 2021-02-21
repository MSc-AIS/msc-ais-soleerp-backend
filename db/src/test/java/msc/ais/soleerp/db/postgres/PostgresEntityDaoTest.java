package msc.ais.soleerp.db.postgres;

import msc.ais.soleerp.db.DaoFactory;
import msc.ais.soleerp.db.EntityDao;
import msc.ais.soleerp.model.AISEntity;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 21/2/21.
 */
public class PostgresEntityDaoTest {

    @Disabled
    @Test
    public void testGetEntities() throws Exception {

        EntityDao entityDao = DaoFactory.createEntityDao();
        List<AISEntity> entityList = entityDao.findEntities("dawdaw");

        entityList.forEach(entity -> System.out.println(entity.getName()));

    }

}
