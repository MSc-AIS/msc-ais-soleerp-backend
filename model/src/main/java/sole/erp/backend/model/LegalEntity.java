package sole.erp.backend.model;

import java.util.List;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 17/1/2021.
 */
public class LegalEntity extends Entity {

    protected String name;
    protected List<NaturalEntity> representativeList;

    public LegalEntity(String commercialActivity) {
        super(commercialActivity);
    }

}
