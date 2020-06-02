package org.cleanpojo.ikkon.fixtures;

import java.util.UUID;

public class ImmutableObject {

    private final UUID id;
    private final String name;

    public ImmutableObject(final UUID id, final String name) {
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
