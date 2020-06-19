package org.cleanpojo.ikkon.specs.defaultvalue;

import java.util.UUID;

public class EntityWithDoubleValue extends Entity {

    private final double value;

    public EntityWithDoubleValue(final UUID id, final String name, final double value) {
        super(id, name);
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
