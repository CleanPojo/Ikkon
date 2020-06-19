package org.cleanpojo.ikkon.specs.defaultvalue;

import java.util.UUID;

public class EntityWithFloatValue extends Entity {

    private final float value;

    public EntityWithFloatValue(final UUID id, final String name, final float value) {
        super(id, name);
        this.value = value;
    }

    public float getValue() {
        return value;
    }
}
