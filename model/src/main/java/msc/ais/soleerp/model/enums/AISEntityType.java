package msc.ais.soleerp.model.enums;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 23/2/2021.
 */
public enum AISEntityType {

    COMPANY("C"), PERSON("P");

    private final String value;

    AISEntityType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static AISEntityType evaluateType(String type) {
        return !Objects.isNull(type)
            ? Arrays.stream(AISEntityType.values()).filter(enumType -> enumType.getValue().equals(type))
            .findAny()
            .orElseThrow(() -> new NoSuchElementException("Error... Unable to find AIS Entity Type for type: " + type))
            : null;
    }
}
