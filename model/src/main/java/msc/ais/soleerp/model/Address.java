package msc.ais.soleerp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import msc.ais.soleerp.model.codelists.CodelistsOfBiMap;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 17/1/2021.
 */
public class Address {

    private final String street;
    private final String streetNumber;
    private final String postalCode;
    private final String city;
    private final String area;
    private final String countryCode;

    private Address(Builder builder) {
        street = builder.street;
        streetNumber = builder.streetNumber;
        postalCode = builder.postalCode;
        city = builder.city;
        area = builder.area;
        countryCode = builder.countryCode;
    }

    public String getStreet() {
        return street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getArea() {
        return area;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountryName() {
        return CodelistsOfBiMap.COUNTRY_CODE_MAP.getValueForId(countryCode);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private String street;
        private String streetNumber;
        private String postalCode;
        private String city;
        private String area;
        private String countryCode;

        public Builder() {
        }

        public Builder street(String street) {
            this.street = street;
            return this;
        }

        public Builder streetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
            return this;
        }

        public Builder postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder area(String area) {
            this.area = area;
            return this;
        }

        public Builder countryCode(String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }

}
