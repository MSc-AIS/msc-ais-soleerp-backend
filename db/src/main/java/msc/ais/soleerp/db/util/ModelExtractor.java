package msc.ais.soleerp.db.util;

import msc.ais.soleerp.db.DaoFactory;
import msc.ais.soleerp.db.jooq.generated.tables.*;
import msc.ais.soleerp.db.jooq.generated.tables.records.*;
import msc.ais.soleerp.model.*;
import msc.ais.soleerp.model.enums.EntityRole;
import msc.ais.soleerp.model.enums.ItemType;
import msc.ais.soleerp.model.enums.UnitOfMeasurementType;
import msc.ais.soleerp.model.internal.AISCountry;
import org.jooq.Record;
import org.jooq.Result;

import java.time.LocalDate;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 27/2/2021.
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

    default AISEntity extractEntity(Result<Record> records) {

        // Get the first entity, all the entities should be the same.
        if (records.size() < 1) {
            throw new NoSuchElementException("Result doAes not contain any records.");
        }

        final AISEntity entity = extractEntity(records.get(0));

        // Check for company, in order to add representatives
        if (entity instanceof LegalAISEntity) {
            LegalAISEntity legalAISEntity = (LegalAISEntity) entity;
            legalAISEntity.getRepresentativeList().addAll(
                DaoFactory.createEntityDao()
                    .findCompanyRepresentatives(legalAISEntity.getId()));
        }

        // loop through all values and extract bank accounts.
        records.forEach(record -> {
            AISBankAccount aisBankAccount = extractBankAccount(record);
            // add if at least the id is not null
            if (!Objects.isNull(aisBankAccount.getId())) {
                entity.getBankAccountList().add(aisBankAccount);
            }
        });

        return entity;
    }

    default AISEntity extractEntity(Record record) {

        Entity en = Entity.ENTITY.as("en");

        final String companyFlag = record.get(en.COMPANY_FLAG);

        switch (companyFlag) {

            case "C": // stands for company
                return LegalAISEntity.builder()
                    .entityId(record.get(en.ENTITY_ID))
                    .name(record.get(en.NAME))
                    .role(extractEntityRole(record.get(en.ROLE)))
                    .taxId(record.get(en.TAX_ID))
                    .taxOffice(extractTaxOffice(record))
                    .address(extractAddress(record))
                    .phoneNumber(record.get(en.PHONE))
                    .website(record.get(en.WEBSITE))
                    .activity(record.get(en.ACTIVITY))
                    .email(record.get(en.EMAIL))
                    .type(companyFlag)
                    .build();

            case "P": // stands for person
                return NaturalAISEntity.builder()
                    .entityId(record.get(en.ENTITY_ID))
                    .name(record.get(en.NAME))
                    .role(extractEntityRole(record.get(en.ROLE)))
                    .taxId(record.get(en.TAX_ID))
                    .taxOffice(extractTaxOffice(record))
                    .address(extractAddress(record))
                    .phoneNumber(record.get(en.PHONE))
                    .website(record.get(en.WEBSITE))
                    .activity(record.get(en.ACTIVITY))
                    .email(record.get(en.EMAIL))
                    .type(companyFlag)
                    .companyId(record.get(en.COMPANY_ID))
                    .build();
        }

        throw new IllegalStateException("Error... Unable to specify company flag.");
    }

    default NaturalAISEntity extractNaturalEntity(EntityRecord entityRecord) {

        final String companyFlag = Objects.requireNonNull(entityRecord.getCompanyFlag());

        if ("P".equals(companyFlag)) { // stands for person
            return NaturalAISEntity.builder()
                .entityId(entityRecord.getEntityId())
                .name(entityRecord.getName())
                // .role(extractEntityRole(entityRecord.getRole()))
                // .taxId(entityRecord.getTaxId())
                // .taxOffice(extractTaxOffice(entityRecord))
                // .address(extractAddress(entityRecord))
                .phoneNumber(entityRecord.getPhone())
                // .website(entityRecord.getWebsite())
                .activity(entityRecord.getActivity())
                // .email(entityRecord.getEmail())
                // .companyId(entityRecord.getCompanyId())
                // .type(companyFlag)
                .build();
        }

        throw new IllegalStateException("Error... Unable to specify company flag.");
    }

    default AISEntity extractEntity(Map<EntityRecord, Result<BankAccountRecord>> recordResultMap) {

        // Get the first entity, all the entities should be the same.
        final AISEntity entity = extractEntity(recordResultMap.keySet().stream()
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("Map is empty, no entity found.")));

        // Check for company, in order to add representatives
        if (entity instanceof LegalAISEntity) {
            LegalAISEntity legalAISEntity = (LegalAISEntity) entity;
            legalAISEntity.getRepresentativeList().addAll(
                DaoFactory.createEntityDao()
                    .findCompanyRepresentatives(legalAISEntity.getId()));
        }

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

        return entity;
    }

    default AISBankAccount extractBankAccount(BankAccountRecord record) {
        return AISBankAccount.builder()
            .bankAccountId(record.getAccountId())
            .swiftCode(record.getSwiftCode())
            .bankCode(record.getBankNameCode())
            .iban(record.getIban())
            .preferable(record.getPreferable())
            .build();
    }

    default AISBankAccount extractBankAccount(Record record) {
        return AISBankAccount.builder()
            .bankAccountId(record.get(BankAccount.BANK_ACCOUNT.ACCOUNT_ID))
            .swiftCode(record.get(BankAccount.BANK_ACCOUNT.SWIFT_CODE))
            .bankName(record.get(ReferenceCodes.REFERENCE_CODES.as("rc1").REF_MEANING))
            .bankCode(record.get(BankAccount.BANK_ACCOUNT.BANK_NAME_CODE))
            .iban(record.get(BankAccount.BANK_ACCOUNT.IBAN))
            .preferable(record.get(BankAccount.BANK_ACCOUNT.PREFERABLE))
            .build();
    }

    default TaxOffice extractTaxOffice(VEntityRecord vEntityRecord) {
        return TaxOffice.builder()
            .name(Objects.requireNonNull(vEntityRecord).get(VEntity.V_ENTITY.TAX_OFFICE))
            .build();
    }

    default TaxOffice extractTaxOffice(EntityRecord entityRecord) {
        return TaxOffice.builder()
            // .name(Objects.requireNonNull(entityRecord).getValue(VEntity.V_ENTITY.TAX_OFFICE))
            .code(Objects.requireNonNull(entityRecord).get(Entity.ENTITY.TAX_OFFICE_CODE))
            .build();
    }

    default TaxOffice extractTaxOffice(Record record) {
        return TaxOffice.builder()
            .name(Objects.requireNonNull(record).get(ReferenceCodes.REFERENCE_CODES.as("rc2").REF_MEANING))
            .code(Objects.requireNonNull(record).get(Entity.ENTITY.TAX_OFFICE_CODE))
            .build();
    }

    default Address extractAddress(VEntityRecord vEntityRecord) {
        return Address.builder()
            .street(Objects.requireNonNull(vEntityRecord).getValue(VEntity.V_ENTITY.STREET))
            .streetNumber(Objects.requireNonNull(vEntityRecord).getValue(VEntity.V_ENTITY.STREET_NO))
            .postalCode(Objects.requireNonNull(vEntityRecord).getValue(VEntity.V_ENTITY.ZIP_CODE))
            .area(Objects.requireNonNull(vEntityRecord).getValue(VEntity.V_ENTITY.AREA))
            .city(Objects.requireNonNull(vEntityRecord).getValue(VEntity.V_ENTITY.CITY))
            .countryCode(Objects.requireNonNull(vEntityRecord).getValue(VEntity.V_ENTITY.COUNTRY_CODE))
            .build();
    }

    default Address extractAddress(Record record) {
        return Address.builder()
            .street(Objects.requireNonNull(record).get(VEntity.V_ENTITY.STREET))
            .streetNumber(Objects.requireNonNull(record).get(VEntity.V_ENTITY.STREET_NO))
            .postalCode(Objects.requireNonNull(record).get(VEntity.V_ENTITY.ZIP_CODE))
            .area(Objects.requireNonNull(record).get(VEntity.V_ENTITY.AREA))
            .city(Objects.requireNonNull(record).get(VEntity.V_ENTITY.CITY))
            .countryCode(Objects.requireNonNull(record).get(VEntity.V_ENTITY.COUNTRY_CODE))
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

    default AISCountry extractCountry(ReferenceCodesRecord record) {
        return AISCountry.builder()
            .countryCode(record.getRefValue())
            .countryName(record.getRefMeaning())
            .build();
    }

    default EntityRecord extractEntityRecord(AISEntity entity) {

        EntityRecord record = new EntityRecord();

        if (!Objects.isNull(entity.getRole())) {
            record.setRole(entity.getRole().name());
        }
        if (!Objects.isNull(entity.getTaxId())) {
            record.setTaxId(entity.getTaxId());
        }
        if (!Objects.isNull(entity.getTaxOffice())
            && !Objects.isNull(entity.getTaxOffice().getCode())) {
            record.setTaxOfficeCode(entity.getTaxOffice().getCode());
        }
        if (!Objects.isNull(entity.getAddress())) {

            if (!Objects.isNull(entity.getAddress().getStreet())) {
                record.setStreet(entity.getAddress().getStreet());
            }
            if (!Objects.isNull(entity.getAddress().getStreetNumber())) {
                record.setStreet(entity.getAddress().getStreetNumber());
            }
            if (!Objects.isNull(entity.getAddress().getPostalCode())) {
                record.setStreet(entity.getAddress().getPostalCode());
            }
            if (!Objects.isNull(entity.getAddress().getCity())) {
                record.setStreet(entity.getAddress().getCity());
            }
            if (!Objects.isNull(entity.getAddress().getArea())) {
                record.setStreet(entity.getAddress().getArea());
            }
            if (!Objects.isNull(entity.getAddress().getCountryCode())) {
                record.setStreet(entity.getAddress().getCountryCode());
            }

        }
        if (!Objects.isNull(entity.getPhoneNumber())) {
            record.setPhone(entity.getPhoneNumber());
        }
        if (!Objects.isNull(entity.getWebsite())) {
            record.setWebsite(entity.getWebsite());
        }
        if (!Objects.isNull(entity.getActivity())) {
            record.setActivity(entity.getActivity());
        }
        if (!Objects.isNull(entity.getEmail())) {
            record.setActivity(entity.getEmail());
        }
        if (!Objects.isNull(entity.getUserId())) {
            record.setUserId(entity.getUserId());
        }


        switch (entity.getType()) {

            case COMPANY:

                return record;

            case PERSON:

                NaturalAISEntity naturalAISEntity = (NaturalAISEntity) entity;

                if (!Objects.isNull(naturalAISEntity.getCompanyId())) {
                    record.setCompanyId(naturalAISEntity.getCompanyId());
                }
                return record;
        }

        throw new IllegalStateException("Error... Unable to specify AISEntity type.");
    }

    default StoreResult extractStoreResult(int storeResult) {

        switch (storeResult) {

            case 0:
                return StoreResult.UNNECESSARY;

            case 1:
                return StoreResult.SUCCESS;

            default:
                return StoreResult.FAILURE;

        }
    }

    default StoreMetadata extractStoreMetadata(int storeResult, int autoGenId) {
        return StoreMetadata.of(autoGenId, extractStoreResult(storeResult));
    }

    default AISItem extractItem(ItemRecord record) {
        return AISItem.builder()
            .id(record.getItemId())
            .typeCode(record.getTypeCode())
            .measurementCode(record.getMeasurementCode())
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

    default AISTransaction extractTransaction(TransactionRecord record) {
        return AISTransaction.builder()
            .id(record.getTransactionId())
            .companyFlag(record.getCompanyFlag())
            .entityId(record.getEntityId())
            .orderNumber(record.get(Transaction.TRANSACTION.ENTITY_ORDER_NO, Integer.class))
            .createdDate(record.get(Transaction.TRANSACTION.DATE_CREATED, LocalDate.class))
            .title(record.getTitle())
            .totalPrice(record.get(Transaction.TRANSACTION.TOTAL_PRICE, Double.class))
            .paymentTerms(record.getPaymentTerms())
            .status(record.getStatus())
            .build();
    }

    default AISTransaction extractTransaction(Record record) {
        Transaction t = Transaction.TRANSACTION;
        return AISTransaction.builder()
            .id(record.get(t.TRANSACTION_ID))
            .companyFlag(record.get(t.COMPANY_FLAG))
            .entityId(record.get(t.ENTITY_ID))
            .orderNumber(record.get(t.ENTITY_ORDER_NO, Integer.class))
            .createdDate(record.get(t.DATE_CREATED, LocalDate.class))
            .title(record.get(t.TITLE))
            .totalPrice(record.get(t.TOTAL_PRICE, Double.class))
            .paymentTerms(record.get(t.PAYMENT_TERMS))
            .status(record.get(t.STATUS))
            .build();
    }

    default AISTransaction extractTransactionWithItems(Result<Record> records) {

        if (records.size() < 1) {
            throw new NoSuchElementException("Result does not contain any records");
        }

        // Extract the transaction
        final AISTransaction transaction = extractTransaction(records.get(0));

        // Extract the transaction items
        records.forEach(record -> transaction.getItemTransactionList().add(extractItemTransaction(record)));

        return transaction;
    }

    default AISItemTransaction extractItemTransaction(Record record) {
        TransactionItems ti = TransactionItems.TRANSACTION_ITEMS;
        return AISItemTransaction.builder()
            .transactionId(record.get(ti.TRANSACTION_ID))
            .item(extractAISItem(record))
            .quantity(record.get(ti.QUANTITY, Double.class))
            .discount(record.get(ti.DISCOUNT, Integer.class))
            .unitPrice(record.get(ti.UNIT_PRICE, Double.class))
            .build();
    }

    default AISItem extractAISItem(Record record) {
        Item i = Item.ITEM;
        return AISItem.builder()
            .id(record.get(i.ITEM_ID))
            .typeCode(record.get(i.TYPE_CODE))
            .measurementCode(record.get(i.MEASUREMENT_CODE))
            .createdDate(record.get(Item.ITEM.DATE_CREATED, LocalDate.class))
            .firstSoldDate(record.get(Item.ITEM.DATE_FIRST_SOLD, LocalDate.class))
            .userId(record.get(i.USER_ID))
            .description(record.get(i.DESCRIPTION))
            .build();
    }

}
