package msc.ais.soleerp.model;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 17/1/2021.
 */
public class Address {

    private final String street;
    private final String streetNumber;
    private final String postalCode;
    private final String city;
    private final String area;
    private final String country;

    private Address(Builder builder) {
        street = builder.street;
        streetNumber = builder.streetNumber;
        postalCode = builder.postalCode;
        city = builder.city;
        area = builder.area;
        country = builder.country;
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

    public String getCountry() {
        return country;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String street;
        private String streetNumber;
        private String postalCode;
        private String city;
        private String area;
        private String country;

        public Builder() {
        }

        public Builder street(String val) {
            street = val;
            return this;
        }

        public Builder streetNumber(String val) {
            streetNumber = val;
            return this;
        }

        public Builder postalCode(String val) {
            postalCode = val;
            return this;
        }

        public Builder city(String val) {
            city = val;
            return this;
        }

        public Builder area(String val) {
            area = val;
            return this;
        }

        public Builder country(String val) {
            country = val;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }

}
