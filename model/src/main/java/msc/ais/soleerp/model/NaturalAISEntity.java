package msc.ais.soleerp.model;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 17/1/2021.
 */
public class NaturalAISEntity extends AISEntity {

    private final int companyId;

    public NaturalAISEntity(Builder builder) {
        super(builder);
        this.companyId = builder.companyId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends AISEntity.Builder<Builder> {

        private int companyId;

        public Builder() {
        }

        @Override
        public Builder getThis() {
            return this;
        }

        public Builder companyId(int id) {
            this.companyId = id;
            return this;
        }

        @Override
        public AISEntity build() {
            return new NaturalAISEntity(this);
        }
    }

}
