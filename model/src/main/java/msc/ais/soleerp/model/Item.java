package msc.ais.soleerp.model;

import msc.ais.soleerp.model.enums.ItemType;
import msc.ais.soleerp.model.enums.UnitOfMeasurementType;

import java.util.Map;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 17/1/2021.
 */
public class Item {

    protected ItemType type;
    protected String title;
    protected String description;
    protected Map<String, String> specificationMap;
    protected UnitOfMeasurementType unitOfMeasurementType;
    protected long createdTimestamp;
    protected long firstTimeSoldTimestamp;
    // protected double netPrice;

    public ItemType getType() {
        return type;
    }
}
