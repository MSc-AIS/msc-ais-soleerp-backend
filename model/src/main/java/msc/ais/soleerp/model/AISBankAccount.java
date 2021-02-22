package msc.ais.soleerp.model;

import java.util.Objects;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 17/1/2021.
 */
public class AISBankAccount {

    private final int id;
    private final String bankName;
    private final String swiftCode;
    private final String iban;
    private final boolean preferable;

    private AISBankAccount(Builder builder) {
        id = builder.id;
        bankName = builder.bankName;
        swiftCode = builder.swiftCode;
        iban = builder.iban;
        preferable = builder.preferable;
    }

    public String getBankName() {
        return bankName;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public String getIban() {
        return iban;
    }

    public boolean isPreferable() {
        return preferable;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private int id;
        private String bankName;
        private String swiftCode;
        private String iban;
        private boolean preferable;

        public Builder bankAccountId(int id) {
            if (id != -1) {
                this.id = id;
            }
            return this;
        }

        public Builder bankName(String bankName) {
            this.bankName = bankName;
            return this;
        }

        public Builder swiftCode(String swiftCode) {
            this.swiftCode = swiftCode;
            return this;
        }

        public Builder iban(String iban) {
            this.iban = iban;
            return this;
        }

        public Builder preferable(boolean preferable) {
            this.preferable = preferable;
            return this;
        }

        public AISBankAccount build() {
            return new AISBankAccount(this);
        }
    }

    @Override
    public String toString() {
        return "AISBankAccount{" +
            "id=" + id +
            ", bankName='" + bankName + '\'' +
            ", swiftCode='" + swiftCode + '\'' +
            ", iban='" + iban + '\'' +
            ", preferable=" + preferable +
            '}';
    }
}
