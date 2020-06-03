package org.cleanpojo.ikkon.fixtures;

import java.util.Collection;
import java.util.UUID;

public class ImmutableCollectionProperty {

    private final UUID id;
    private final String name;
    private final Collection<String> values;

    public ImmutableCollectionProperty(final UUID id, final String name, final Collection<String> values) {
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

    public Collection<String> getValues() {
        return values;
    }
}
