package msc.ais.soleerp.model;

import java.time.LocalDate;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 17/1/2021.
 */
public class AISItem {

    private Integer id;
    private String typeCode;
    private String description;
    private String measurementCode;
    private LocalDate createdDate;
    private LocalDate firstSoldDate;
    private Integer userId;

    public AISItem() {

    }

    private AISItem(Builder builder) {
        this.id = builder.id;
        this.description = builder.description;
        this.createdDate = builder.createdDate;
        this.firstSoldDate = builder.firstSoldDate;
        this.userId = builder.userId;
        this.typeCode = builder.typeCode;
        this.measurementCode = builder.measurementCode;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public String getDescription() {
        return description;
    }

    public String getMeasurementCode() {
        return measurementCode;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public LocalDate getFirstSoldDate() {
        return firstSoldDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMeasurementCode(String measurementCode) {
        this.measurementCode = measurementCode;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public void setFirstSoldDate(LocalDate firstSoldDate) {
        this.firstSoldDate = firstSoldDate;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer id;
        private String description;
        private LocalDate createdDate;
        private LocalDate firstSoldDate;
        private Integer userId;
        private String typeCode;
        private String measurementCode;

        public Builder() {
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder typeCode(String typeCode) {
            this.typeCode = typeCode;
            return this;
        }

        public Builder measurementCode(String measurementCode) {
            this.measurementCode = measurementCode;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder createdDate(LocalDate createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder firstSoldDate(LocalDate firstSoldDate) {
            this.firstSoldDate = firstSoldDate;
            return this;
        }

        public Builder userId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public AISItem build() {
            return new AISItem(this);
        }
    }
}
