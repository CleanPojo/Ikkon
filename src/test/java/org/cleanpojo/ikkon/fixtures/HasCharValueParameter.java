package org.cleanpojo.ikkon.fixtures;

import java.util.UUID;

public class HasCharValueParameter extends Immutable {

    private final char value;

    public HasCharValueParameter(final UUID id, final String name, final char value) {
        super(id, name);
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}
