package msc.ais.soleerp.db.util;

import msc.ais.soleerp.db.jooq.generated.tables.records.ItemRecord;
import msc.ais.soleerp.model.AISItem;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 26/2/2021.
 */
public interface ItemModelExtractor {

    default ItemRecord extractItemRecord(AISItem item) {
        ItemRecord record = new ItemRecord();

        record.setUserId(item.getUserId());
        record.setTypeCode(item.getItemCode());
        record.setDescription(item.getDescription());
        record.setMeasurementCode(item.getMeasurementCode());

        return record;
    }

}
