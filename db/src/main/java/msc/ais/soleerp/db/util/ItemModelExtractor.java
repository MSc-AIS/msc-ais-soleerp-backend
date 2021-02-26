package msc.ais.soleerp.db.util;

import msc.ais.soleerp.db.jooq.generated.tables.Item;
import msc.ais.soleerp.db.jooq.generated.tables.records.ItemRecord;
import msc.ais.soleerp.model.AISItem;
import msc.ais.soleerp.model.enums.ItemType;
import msc.ais.soleerp.model.enums.UnitOfMeasurementType;

import java.time.LocalDate;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 26/2/2021.
 */
public interface ItemModelExtractor {

    default AISItem extractItem(ItemRecord record) {
        return AISItem.builder()
            .id(record.getItemId())
            .itemType(extractItemType(record.getTypeCode()))
            .unitOfMeasurementType(extractUnitOfMeasurementType(record.getMeasurementCode()))
            .createdDate(record.get(Item.ITEM.DATE_CREATED, LocalDate.class))
            .firstSoldDate(record.get(Item.ITEM.DATE_FIRST_SOLD, LocalDate.class))
            .userId(record.getUserId())
            .description(record.getDescription())
            .build();
    }

    default UnitOfMeasurementType extractUnitOfMeasurementType(String type) {

        switch (type) {

            case "KG":
                return UnitOfMeasurementType.PER_KILO;

            case "EA":
                return UnitOfMeasurementType.QUANTITY;

            case "WE":
                return UnitOfMeasurementType.PER_WEEK;

            case "MR":
                return UnitOfMeasurementType.METRES;

            case "DA":
                return UnitOfMeasurementType.PER_DAY;

            case "MO":
                return UnitOfMeasurementType.PER_MONTH;

            case "YE":
                return UnitOfMeasurementType.PER_YEAR;

        }

        throw new IllegalStateException(
            "Error... Unable to specify unit of measurement type.");
    }

    default ItemType extractItemType(String type) {

        switch (type) {

            case "S":
                return ItemType.SERVICE;

            case "I":
                return ItemType.IMMATERIAL_PRODUCT;

        }

        throw new IllegalStateException("Error... Unable to specify item type.");
    }

}
