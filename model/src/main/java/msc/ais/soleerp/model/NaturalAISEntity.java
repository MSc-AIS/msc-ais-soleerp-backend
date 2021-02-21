package msc.ais.soleerp.model;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 17/1/2021.
 */
public class NaturalAISEntity extends AISEntity {

    public NaturalAISEntity(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends AISEntity.Builder<Builder> {

        public Builder() {
        }

        @Override
        public Builder getThis() {
            return this;
        }

        @Override
        public AISEntity build() {
            return new NaturalAISEntity(this);
        }
    }

}
