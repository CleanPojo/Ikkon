package org.cleanpojo.ikkon.fixtures;

import java.util.List;
import java.util.UUID;

public class ImmutableListProperty {

    private final UUID id;
    private final String name;
    private final List<String> values;

    public ImmutableListProperty(final UUID id, final String name, final List<String> values) {
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

    public List<String> getValues() {
        return values;
    }
}
