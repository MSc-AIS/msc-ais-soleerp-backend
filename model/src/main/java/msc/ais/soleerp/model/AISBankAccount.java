package msc.ais.soleerp.model;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 17/1/2021.
 */
public class AISBankAccount {

    private final String bankName;
    private final String swiftCode;
    private final String iban;
    private final boolean preferable;

    private AISBankAccount(Builder builder) {
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

    public static final class Builder {

        private String bankName;
        private String swiftCode;
        private String iban;
        private boolean preferable;

        public Builder() {
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
}
