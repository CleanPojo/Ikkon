package org.cleanpojo.ikkon.specs.constructor;

import java.util.UUID;

public class Entity {

    private final UUID id;
    private final String name;

    public Entity(final UUID id, final String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
