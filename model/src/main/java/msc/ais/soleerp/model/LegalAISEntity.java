package msc.ais.soleerp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 17/1/2021.
 */
public class LegalAISEntity extends AISEntity {

    private List<NaturalAISEntity> representativeList;

    protected LegalAISEntity(Builder builder) {
        super(builder);
    }

    public List<NaturalAISEntity> getRepresentativeList() {
        if (Objects.isNull(representativeList)) {
            representativeList = new ArrayList<>();
        }
        return representativeList;
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
            return new LegalAISEntity(this);
        }
    }
}
