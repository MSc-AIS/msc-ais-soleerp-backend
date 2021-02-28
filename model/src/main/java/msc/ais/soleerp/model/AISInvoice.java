package msc.ais.soleerp.model;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 17/1/2021.
 */
public class AISInvoice {

    private String id;
    private AISTransaction transaction;
    private AISUser user;
    private String row;
    private String notes;

    public AISInvoice() {

    }

    private AISInvoice(Builder builder) {
        id = builder.id;
        transaction = builder.transaction;
        user = builder.user;
        row = builder.row;
        notes = builder.notes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AISTransaction getTransaction() {
        return transaction;
    }

    public void setTransaction(AISTransaction transaction) {
        this.transaction = transaction;
    }

    public AISUser getUser() {
        return user;
    }

    public void setUser(AISUser user) {
        this.user = user;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private String id;
        private AISTransaction transaction;
        private AISUser user;
        private String row;
        private String notes;

        public Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder transaction(AISTransaction transaction) {
            this.transaction = transaction;
            return this;
        }

        public Builder user(AISUser user) {
            this.user = user;
            return this;
        }

        public Builder row(String row) {
            this.row = row;
            return this;
        }

        public Builder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public AISInvoice build() {
            return new AISInvoice(this);
        }
    }
}
