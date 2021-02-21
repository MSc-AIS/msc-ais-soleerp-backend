package msc.ais.soleerp.model;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 17/1/2021.
 */
public class TaxOffice {

    /**
     * Tax office code.
     */
    private final String code;
    private final String name;

    private TaxOffice(Builder builder) {
        this.code = builder.code;
        this.name = builder.name;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String code;
        private String name;

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public TaxOffice build() {
            return new TaxOffice(this);
        }

    }


}
