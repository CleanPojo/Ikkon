package org.cleanpojo.ikkon.fixtures;

import java.util.UUID;

public class HasShortValueParameter extends Immutable {

    private final short value;

    public HasShortValueParameter(final UUID id, final String name, final short value) {
        super(id, name);
        this.value = value;
    }

    public short getValue() {
        return value;
    }
}
