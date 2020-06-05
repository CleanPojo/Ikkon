package org.cleanpojo.ikkon.fixtures;

import java.beans.ConstructorProperties;
import java.util.UUID;

public class DecoratedWithConstructorProperties {

    private final UUID id;
    private final String name;

    @ConstructorProperties({ "id", "name" })
    public DecoratedWithConstructorProperties(final UUID arg1, final String arg2) {
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
