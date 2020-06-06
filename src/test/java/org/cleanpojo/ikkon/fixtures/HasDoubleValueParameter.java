package org.cleanpojo.ikkon.fixtures;

import java.util.UUID;

public class HasDoubleValueParameter extends Immutable {

    private final double value;

    public HasDoubleValueParameter(final UUID id, final String name, final double value) {
        super(id, name);
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
