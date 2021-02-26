package msc.ais.soleerp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import msc.ais.soleerp.model.enums.ItemType;
import msc.ais.soleerp.model.enums.UnitOfMeasurementType;

import java.time.LocalDate;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 17/1/2021.
 */
public class AISItem {

    private Integer id;
    private ItemType itemType;
    private String itemCode;
    private String description;
    private UnitOfMeasurementType unitOfMeasurementType;
    private String measurementCode;
    private LocalDate createdDate;
    private LocalDate firstSoldDate;
    private Integer userId;

    public AISItem() {

    }

    private AISItem(Builder builder) {
        this.id = builder.id;
        this.itemType = builder.itemType;
        this.description = builder.description;
        this.unitOfMeasurementType = builder.unitOfMeasurementType;
        this.createdDate = builder.createdDate;
        this.firstSoldDate = builder.firstSoldDate;
        this.userId = builder.userId;
    }

    public Integer getId() {
        return id;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public Integer getUserId() {
        return userId;
    }

    // @JsonIgnore
    public String getItemCode() {
        return itemCode;
    }

    public String getDescription() {
        return description;
    }

    public UnitOfMeasurementType getUnitOfMeasurementType() {
        return unitOfMeasurementType;
    }

    // @JsonIgnore
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

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
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
        private ItemType itemType;
        private String description;
        private UnitOfMeasurementType unitOfMeasurementType;
        private LocalDate createdDate;
        private LocalDate firstSoldDate;
        private Integer userId;

        public Builder() {
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder itemType(ItemType itemType) {
            this.itemType = itemType;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder unitOfMeasurementType(UnitOfMeasurementType unitOfMeasurementType) {
            this.unitOfMeasurementType = unitOfMeasurementType;
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
