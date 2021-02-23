package msc.ais.soleerp.db.util;

import msc.ais.soleerp.db.jooq.generated.tables.Entity;
import msc.ais.soleerp.db.jooq.generated.tables.VEntity;
import msc.ais.soleerp.db.jooq.generated.tables.records.BankAccountRecord;
import msc.ais.soleerp.db.jooq.generated.tables.records.EntityRecord;
import msc.ais.soleerp.db.jooq.generated.tables.records.VEntityRecord;
import msc.ais.soleerp.model.*;
import msc.ais.soleerp.model.enums.EntityRole;
import org.jooq.Record;
import org.jooq.Result;

import java.util.*;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 21/2/21.
 */
public interface ModelExtractor {

    default AISEntity extractEntity(VEntityRecord vEntityRecord) {

        final String companyFlag = Objects.requireNonNull(vEntityRecord.getCompanyFlag());

        switch (companyFlag) {

            case "C": // stands for company
                return LegalAISEntity.builder()
                    .entityId(vEntityRecord.getEntityId())
                    .name(vEntityRecord.getName())
                    .role(extractEntityRole(vEntityRecord.getRole()))
                    .taxId(vEntityRecord.getTaxId())
                    .taxOffice(extractTaxOffice(vEntityRecord))
                    .address(extractAddress(vEntityRecord))
                    .phoneNumber(vEntityRecord.getPhone())
                    .website(vEntityRecord.getWebsite())
                    .activity(vEntityRecord.getActivity())
                    .email(vEntityRecord.getEmail())
                    .type(companyFlag)
                    .build();

            case "P": // stands for person
                return NaturalAISEntity.builder()
                    .entityId(vEntityRecord.getEntityId())
                    .name(vEntityRecord.getName())
                    .role(extractEntityRole(vEntityRecord.getRole()))
                    .taxId(vEntityRecord.getTaxId())
                    .taxOffice(extractTaxOffice(vEntityRecord))
                    .address(extractAddress(vEntityRecord))
                    .phoneNumber(vEntityRecord.getPhone())
                    .website(vEntityRecord.getWebsite())
                    .activity(vEntityRecord.getActivity())
                    .email(vEntityRecord.getEmail())
                    .type(companyFlag)
                    .build();

        }

        throw new IllegalStateException("Error... Unable to specify company flag.");
    }

    default AISEntity extractEntity(EntityRecord entityRecord) {

        final String companyFlag = Objects.requireNonNull(entityRecord.getCompanyFlag());

        switch (companyFlag) {

            case "C": // stands for company
                return LegalAISEntity.builder()
                    .entityId(entityRecord.getEntityId())
                    .name(entityRecord.getName())
                    .role(extractEntityRole(entityRecord.getRole()))
                    .taxId(entityRecord.getTaxId())
                    .taxOffice(extractTaxOffice(entityRecord))
                    .address(extractAddress(entityRecord))
                    .phoneNumber(entityRecord.getPhone())
                    .website(entityRecord.getWebsite())
                    .activity(entityRecord.getActivity())
                    .email(entityRecord.getEmail())
                    .type(companyFlag)
                    .build();

            case "P": // stands for person
                return NaturalAISEntity.builder()
                    .entityId(entityRecord.getEntityId())
                    .name(entityRecord.getName())
                    .role(extractEntityRole(entityRecord.getRole()))
                    .taxId(entityRecord.getTaxId())
                    .taxOffice(extractTaxOffice(entityRecord))
                    .address(extractAddress(entityRecord))
                    .phoneNumber(entityRecord.getPhone())
                    .website(entityRecord.getWebsite())
                    .activity(entityRecord.getActivity())
                    .email(entityRecord.getEmail())
                    .companyId(entityRecord.getCompanyId())
                    .type(companyFlag)
                    .build();
        }

        throw new IllegalStateException("Error... Unable to specify company flag.");
    }

    default AISEntity extractEntity(Map<EntityRecord, Result<BankAccountRecord>> recordResultMap) {

        // Get the first entity, all the entities should be the same.
        final AISEntity entity = extractEntity(recordResultMap.keySet().stream()
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("Map is empty, no entity found.")));

        // loop through all values and extract bank accounts.
        recordResultMap.values().forEach(bankAccountRecords ->
            bankAccountRecords.forEach(bankAccountRecord -> {
                    AISBankAccount aisBankAccount = extractBankAccount(bankAccountRecord);
                    // add if at least the id is not null
                    if (!Objects.isNull(aisBankAccount.getId())) {
                        entity.getBankAccountList().add(aisBankAccount);
                    }
                }
            ));

        System.out.println("Size of bank account list is: " + entity.getBankAccountList().size());

        return entity;
    }

    default AISBankAccount extractBankAccount(BankAccountRecord record) {
        return AISBankAccount.builder()
            .bankAccountId(record.getAccountId())
            .swiftCode(record.getSwiftCode())
            .bankName(record.getBankNameCode())
            .iban(record.getIban())
            .preferable(record.getPreferable())
            .build();
    }

    default TaxOffice extractTaxOffice(VEntityRecord vEntityRecord) {
        return TaxOffice.builder()
            .name(Objects.requireNonNull(vEntityRecord).getValue(VEntity.V_ENTITY.TAX_OFFICE))
            .build();
    }

    default TaxOffice extractTaxOffice(EntityRecord entityRecord) {
        return TaxOffice.builder()
            // .name(Objects.requireNonNull(entityRecord).getValue(VEntity.V_ENTITY.TAX_OFFICE))
            .code(Objects.requireNonNull(entityRecord).getValue(Entity.ENTITY.TAX_OFFICE_CODE))
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

    default Address extractAddress(Record record) {
        return Address.builder()
            .street(Objects.requireNonNull(record).getValue(VEntity.V_ENTITY.STREET))
            .streetNumber(Objects.requireNonNull(record).getValue(VEntity.V_ENTITY.STREET_NO))
            .postalCode(Objects.requireNonNull(record).getValue(VEntity.V_ENTITY.ZIP_CODE))
            .area(Objects.requireNonNull(record).getValue(VEntity.V_ENTITY.AREA))
            .city(Objects.requireNonNull(record).getValue(VEntity.V_ENTITY.CITY))
            .country(Objects.requireNonNull(record).getValue(VEntity.V_ENTITY.COUNTRY_CODE))
            .build();
    }

    default EntityRole extractEntityRole(String role) {

        switch (role) {

            case "C": // stands for customer
                return EntityRole.CUSTOMER;

            case "S": // stands for supplier
                return EntityRole.SUPPLIER;

            case "B": // stands for both
                return EntityRole.CUSTOMER_SUPPLIER;
        }

        throw new IllegalStateException("Error... Unable to specify entity role.");
    }

}
