package org.cleanpojo.ikkon.specs.defaultvalue;

import java.util.UUID;

public class EntityWithIntValue extends Entity {

    private final int value;

    public EntityWithIntValue(final UUID id, final String name, final int value) {
        super(id, name);
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
