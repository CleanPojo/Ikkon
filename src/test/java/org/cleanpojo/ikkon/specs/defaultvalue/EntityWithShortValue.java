package org.cleanpojo.ikkon.specs.defaultvalue;

import java.util.UUID;

public class EntityWithShortValue extends Entity {

    private final short value;

    public EntityWithShortValue(final UUID id, final String name, final short value) {
        super(id, name);
        this.value = value;
    }

    public short getValue() {
        return value;
    }
}
