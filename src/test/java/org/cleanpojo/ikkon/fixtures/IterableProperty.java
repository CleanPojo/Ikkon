package org.cleanpojo.ikkon.fixtures;

import java.util.UUID;

public class IterableProperty {

    private final UUID id;
    private final String name;
    private final Iterable<String> values;

    public IterableProperty(final UUID id, final String name, final Iterable<String> values) {
        this.id = id;
        this.name = name;
        this.values = values;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Iterable<String> getValues() {
        return values;
    }
}
