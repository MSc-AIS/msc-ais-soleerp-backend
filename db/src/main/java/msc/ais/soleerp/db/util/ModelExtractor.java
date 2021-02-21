package msc.ais.soleerp.db.util;

import msc.ais.soleerp.db.jooq.generated.tables.VEntity;
import msc.ais.soleerp.db.jooq.generated.tables.records.VEntityRecord;
import msc.ais.soleerp.model.*;

import java.util.Objects;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 21/2/21.
 */
public interface ModelExtractor {

    default AISEntity extractAISEntity(VEntityRecord vEntityRecord) {

        switch (vEntityRecord.getCompanyFlag()) {

            case "C":
                return LegalAISEntity.builder()
                    .entityId(Objects.requireNonNull(vEntityRecord).getValue(VEntity.V_ENTITY.ENTITY_ID))
                    .name(Objects.requireNonNull(vEntityRecord).getValue(VEntity.V_ENTITY.NAME))
                    .role(extractEntityRole(Objects.requireNonNull(vEntityRecord).getValue(VEntity.V_ENTITY.ROLE)))
                    .taxId(Objects.requireNonNull(vEntityRecord).getValue(VEntity.V_ENTITY.TAX_ID))
                    .taxOffice(extractTaxOffice(vEntityRecord))
                    .address(extractAddress(vEntityRecord))
                    .phoneNumber(Objects.requireNonNull(vEntityRecord).getValue(VEntity.V_ENTITY.PHONE))
                    .website(Objects.requireNonNull(vEntityRecord).getValue(VEntity.V_ENTITY.WEBSITE))
                    .activity(Objects.requireNonNull(vEntityRecord).getValue(VEntity.V_ENTITY.ACTIVITY))
                    .email(Objects.requireNonNull(vEntityRecord).getValue(VEntity.V_ENTITY.EMAIL))
                    .build();

            case "P":
                return NaturalAISEntity.builder()
                    .entityId(Objects.requireNonNull(vEntityRecord).getValue(VEntity.V_ENTITY.ENTITY_ID))
                    .name(Objects.requireNonNull(vEntityRecord).getValue(VEntity.V_ENTITY.NAME))
                    .role(extractEntityRole(Objects.requireNonNull(vEntityRecord).getValue(VEntity.V_ENTITY.ROLE)))
                    .taxId(Objects.requireNonNull(vEntityRecord).getValue(VEntity.V_ENTITY.TAX_ID))
                    .taxOffice(extractTaxOffice(vEntityRecord))
                    .address(extractAddress(vEntityRecord))
                    .phoneNumber(Objects.requireNonNull(vEntityRecord).getValue(VEntity.V_ENTITY.PHONE))
                    .website(Objects.requireNonNull(vEntityRecord).getValue(VEntity.V_ENTITY.WEBSITE))
                    .activity(Objects.requireNonNull(vEntityRecord).getValue(VEntity.V_ENTITY.ACTIVITY))
                    .email(Objects.requireNonNull(vEntityRecord).getValue(VEntity.V_ENTITY.EMAIL))
                    .build();

        }

        throw new IllegalStateException("Error... Unable to specify company flag.");
    }

    default TaxOffice extractTaxOffice(VEntityRecord vEntityRecord) {
        return TaxOffice.builder()
            .name(Objects.requireNonNull(vEntityRecord).getValue(VEntity.V_ENTITY.TAX_OFFICE))
            .build();
    }

    default Address extractAddress(VEntityRecord vEntityRecord) {
        return Address.builder()
            .street(Objects.requireNonNull(vEntityRecord).getValue(VEntity.V_ENTITY.STREET))
            .streetNumber(Objects.requireNonNull(vEntityRecord).getValue(VEntity.V_ENTITY.STREET_NO))
            .postalCode(Objects.requireNonNull(vEntityRecord).getValue(VEntity.V_ENTITY.ZIP_CODE))
            .area(Objects.requireNonNull(vEntityRecord).getValue(VEntity.V_ENTITY.AREA))
            .city(Objects.requireNonNull(vEntityRecord).getValue(VEntity.V_ENTITY.CITY))
            .country(Objects.requireNonNull(vEntityRecord).getValue(VEntity.V_ENTITY.COUNTRY_CODE))
            .build();
    }

    default EntityRole extractEntityRole(String role) {

//        switch (role) {
//
//            case "C": // stands for customer
//                return EntityRole.CUSTOMER;
//
//            case "S": // stands for supplier
//                return EntityRole.SUPPLIER;
//
//            case "B": // stands for both
//                return EntityRole.CUSTOMER_SUPPLIER;
//        }
//
//        throw new IllegalStateException("Error... Unable to specify entity role.");

        return EntityRole.CUSTOMER;
    }

}
