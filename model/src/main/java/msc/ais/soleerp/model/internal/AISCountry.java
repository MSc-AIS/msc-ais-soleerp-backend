package msc.ais.soleerp.model.internal;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 23/2/2021.
 */
public class AISCountry {

    private final String countryName;
    private final String countryCode;

    private AISCountry(Builder builder) {
        countryName = builder.countryName;
        countryCode = builder.countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String countryName;
        private String countryCode;

        public Builder() {
        }

        public Builder countryName(String countryName) {
            this.countryName = countryName;
            return this;
        }

        public Builder countryCode(String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public AISCountry build() {
            return new AISCountry(this);
        }
    }
}
