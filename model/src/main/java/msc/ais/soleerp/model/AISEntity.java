package msc.ais.soleerp.model;

import msc.ais.soleerp.model.enums.EntityRole;
import msc.ais.soleerp.model.enums.AISEntityType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 17/1/2021.
 */
public abstract class AISEntity {

    protected int id;
    protected String name;
    protected EntityRole role;
    protected long taxId;
    protected TaxOffice taxOffice;
    protected Address address;
    protected String phoneNumber;
    protected String email;
    protected String website;
    protected AISEntityType type;
    /**
     * Entity's main activity code
     */
    protected String activity;
    protected List<AISBankAccount> bankAccountList;

    protected AISEntity(Builder<?> builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.role = builder.role;
        this.taxId = builder.taxId;
        this.taxOffice = builder.taxOffice;
        this.address = builder.address;
        this.phoneNumber = builder.phoneNumber;
        this.website = builder.website;
        this.email = builder.email;
        this.activity = builder.activity;
        this.type = AISEntityType.evaluateType(builder.type);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public EntityRole getRole() {
        return role;
    }

    public long getTaxId() {
        return taxId;
    }

    public TaxOffice getTaxOffice() {
        return taxOffice;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public String getActivity() {
        return activity;
    }

    public AISEntityType getType() {
        return type;
    }

    public List<AISBankAccount> getBankAccountList() {
        if (Objects.isNull(bankAccountList)) {
            bankAccountList = new ArrayList<>();
        }
        return bankAccountList;
    }

    protected abstract static class Builder<T extends Builder<T>> {

        private int id;
        private String name;
        private EntityRole role;
        private long taxId;
        private TaxOffice taxOffice;
        private Address address;
        private String phoneNumber;
        private String email;
        private String website;
        private String activity;
        private String type;

        public Builder() {
        }

        public T website(String website) {
            this.website = website;
            return getThis();
        }

        public T email(String email) {
            this.email = email;
            return getThis();
        }

        public T taxId(long taxId) {
            this.taxId = taxId;
            return getThis();
        }

        public T taxOffice(TaxOffice taxOffice) {
            this.taxOffice = taxOffice;
            return getThis();
        }

        public T address(Address address) {
            this.address = address;
            return getThis();
        }

        public T phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return getThis();
        }

        public T entityId(int id) {
            this.id = id;
            return getThis();
        }

        public T name(String name) {
            this.name = name;
            return getThis();
        }

        public T role(EntityRole role) {
            this.role = role;
            return getThis();
        }

        public T type(String type) {
            this.type = type;
            return getThis();
        }

        /**
         * Commercial main activity.
         *
         * @param activity The main activity
         * @return The Builder
         */
        public T activity(String activity) {
            this.activity = activity;
            return getThis();
        }

        public abstract T getThis();

        public abstract AISEntity build();
    }


}
