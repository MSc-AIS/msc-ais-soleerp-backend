package msc.ais.soleerp.model.enums;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 23/2/2021.
 */

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Use to determine which bank account is the preferable one
 */
public enum PreferableType {

    PREFERABLE("1"), NOT_PREFERABLE("0");

    private final String value;

    PreferableType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PreferableType evaluateType(String type) {
        return !Objects.isNull(type)
            ? Arrays.stream(PreferableType.values()).filter(enumType -> enumType.getValue().equals(type))
            .findAny()
            .orElseThrow(() -> new NoSuchElementException("Error... Unable to find Preferable Type for type: " + type))
            : null;
    }

}
