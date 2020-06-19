package org.cleanpojo.ikkon.specs.basic;

import java.util.UUID;

public class ImmutableEntity {

    private final UUID id;
    private final String name;

    public ImmutableEntity(final UUID id, final String name) {
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
