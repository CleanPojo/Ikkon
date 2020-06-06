package org.cleanpojo.ikkon.fixtures;

import java.util.UUID;

public class HasIntValueParameter extends Immutable {

    private final int value;

    public HasIntValueParameter(final UUID id, final String name, final int value) {
        super(id, name);
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
