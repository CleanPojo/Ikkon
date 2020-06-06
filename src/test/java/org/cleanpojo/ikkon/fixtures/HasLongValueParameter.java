package org.cleanpojo.ikkon.fixtures;

import java.util.UUID;

public class HasLongValueParameter extends Immutable {

    private final long value;

    public HasLongValueParameter(final UUID id, final String name, final long value) {
        super(id, name);
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}
