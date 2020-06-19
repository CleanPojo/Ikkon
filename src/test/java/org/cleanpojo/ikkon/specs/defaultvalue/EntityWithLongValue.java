package org.cleanpojo.ikkon.specs.defaultvalue;

import java.util.UUID;

public class EntityWithLongValue extends Entity {

    private final long value;

    public EntityWithLongValue(final UUID id, final String name, final long value) {
        super(id, name);
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}
