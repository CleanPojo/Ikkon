package org.cleanpojo.ikkon.specs.defaultvalue;

import java.util.UUID;

public class EntityWithByteValue extends Entity {

    private final byte value;

    public EntityWithByteValue(final UUID id, final String name, final byte value) {
        super(id, name);
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}
