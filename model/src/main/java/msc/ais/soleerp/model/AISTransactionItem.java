package msc.ais.soleerp.model;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 27/2/2021.
 */
public class AISTransactionItem {

    private AISItem item;
    private Integer transactionId;
    private Double unitPrice;
    private Integer discount;
    private Double quantity;

    public AISTransactionItem() {

    }

    private AISTransactionItem(Builder builder) {
        item = builder.item;
        transactionId = builder.transactionId;
        unitPrice = builder.unitPrice;
        discount = builder.discount;
        quantity = builder.quantity;
    }

    public AISItem getItem() {
        return item;
    }

    public void setItem(AISItem item) {
        this.item = item;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private AISItem item;
        private Integer transactionId;
        private Double unitPrice;
        private Integer discount;
        private Double quantity;

        public Builder() {
        }

        public Builder item(AISItem item) {
            this.item = item;
            return this;
        }

        public Builder transactionId(Integer transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public Builder unitPrice(Double unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public Builder discount(Integer discount) {
            this.discount = discount;
            return this;
        }

        public Builder quantity(Double quantity) {
            this.quantity = quantity;
            return this;
        }

        public AISTransactionItem build() {
            return new AISTransactionItem(this);
        }
    }
}
