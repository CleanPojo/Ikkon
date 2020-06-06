package org.cleanpojo.ikkon.fixtures;

import java.util.UUID;

public class HasFloatValueParameter extends Immutable {

    private final float value;

    public HasFloatValueParameter(final UUID id, final String name, final float value) {
        super(id, name);
        this.value = value;
    }

    public float getValue() {
        return value;
    }
}
