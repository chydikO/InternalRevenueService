package org.chudnovskiy0;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode

public class City {
    private final String label;
    private final int value;

    @Override
    public String toString() {
        return label + " - " + value;
    }
}
