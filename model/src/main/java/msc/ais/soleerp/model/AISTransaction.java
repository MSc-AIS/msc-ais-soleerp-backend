package msc.ais.soleerp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 20/1/2021.
 */
public class AISTransaction {

    private Integer id;
    private Integer entityId;
    private String title;
    private String orderNumber;
    private String companyFlag;
    private LocalDate createdDate;
    private String paymentTerms;
    private Double totalPrice;
    private String status;

    private List<AISItemTransaction> itemTransactionList;

    public AISTransaction() {

    }

    private AISTransaction(Builder builder) {
        id = builder.id;
        entityId = builder.entityId;
        title = builder.title;
        orderNumber = builder.orderNumber;
        companyFlag = builder.companyFlag;
        createdDate = builder.createdDate;
        paymentTerms = builder.paymentTerms;
        totalPrice = builder.totalPrice;
        status = builder.status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCompanyFlag() {
        return companyFlag;
    }

    public void setCompanyFlag(String companyFlag) {
        this.companyFlag = companyFlag;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<AISItemTransaction> getItemTransactionList() {
        if (Objects.isNull(itemTransactionList)) {
            itemTransactionList = new ArrayList<>();
        }
        return itemTransactionList;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private Integer id;
        private Integer entityId;
        private String title;
        private String orderNumber;
        private String companyFlag;
        private LocalDate createdDate;
        private String paymentTerms;
        private Double totalPrice;
        private String status;

        public Builder() {
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder entityId(Integer entityId) {
            this.entityId = entityId;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder orderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
            return this;
        }

        public Builder companyFlag(String companyFlag) {
            this.companyFlag = companyFlag;
            return this;
        }

        public Builder createdDate(LocalDate createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder paymentTerms(String paymentTerms) {
            this.paymentTerms = paymentTerms;
            return this;
        }

        public Builder totalPrice(Double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public AISTransaction build() {
            return new AISTransaction(this);
        }
    }
}
