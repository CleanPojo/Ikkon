package org.cleanpojo.ikkon.specs.constructor;

import java.beans.ConstructorProperties;
import java.util.UUID;

public class EntityWithConstructorProperties {

    private final UUID id;
    private final String name;

    @ConstructorProperties({ "id", "name" })
    public EntityWithConstructorProperties(final UUID arg1, final String arg2) {
        this.id = arg1;
        this.name = arg2;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
