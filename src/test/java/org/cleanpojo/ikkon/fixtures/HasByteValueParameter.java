package org.cleanpojo.ikkon.fixtures;

import java.util.UUID;

public class HasByteValueParameter extends Immutable {

    private final byte value;

    public HasByteValueParameter(final UUID id, final String name, final byte value) {
        super(id, name);
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}
