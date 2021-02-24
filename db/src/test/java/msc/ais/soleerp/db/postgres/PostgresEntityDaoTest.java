package msc.ais.soleerp.db.postgres;

import msc.ais.soleerp.db.DaoFactory;
import msc.ais.soleerp.db.EntityDao;
import msc.ais.soleerp.model.AISEntity;
import msc.ais.soleerp.model.NaturalAISEntity;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 21/2/21.
 */
public class PostgresEntityDaoTest {

    @Disabled
    @Test
    public void testFindEntities() {

        EntityDao entityDao = DaoFactory.createEntityDao();
        List<AISEntity> entityList = entityDao.findEntities("dawdaw");

        entityList.forEach(entity -> System.out.println(entity.getName()));

    }

    @Test
    public void testFindEntityById() {

        EntityDao entityDao = DaoFactory.createEntityDao();
        entityDao.findEntityById(4, 35);

    }

    @Disabled
    @Test
    public void deleteEntityById() {

        EntityDao entityDao = DaoFactory.createEntityDao();
        int rowsDeleted = entityDao.deleteEntityById(2, 35);
        System.out.println("Rows deleted: " + rowsDeleted);
    }

    // @Disabled
    @Test
    public void updateEntityById() {

        EntityDao entityDao = DaoFactory.createEntityDao();
        int rowsUpdated = entityDao.updateEntityById(4, 35,
            NaturalAISEntity.builder()
                .type("P")
                .taxId(88888L)
                .build());
        System.out.println("Rows deleted: " + rowsUpdated);
    }

}
