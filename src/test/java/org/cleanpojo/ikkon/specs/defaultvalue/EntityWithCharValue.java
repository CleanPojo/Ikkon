package org.cleanpojo.ikkon.specs.defaultvalue;

import java.util.UUID;

public class EntityWithCharValue extends Entity {

    private final char value;

    public EntityWithCharValue(final UUID id, final String name, final char value) {
        super(id, name);
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}
