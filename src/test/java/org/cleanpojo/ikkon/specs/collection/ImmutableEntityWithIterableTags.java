package org.cleanpojo.ikkon.specs.collection;

import java.util.UUID;

public class ImmutableEntityWithIterableTags {

    private final UUID id;
    private final String name;
    private final Iterable<String> tags;

    public ImmutableEntityWithIterableTags(
        final UUID id,
        final String name,
        final Iterable<String> tags) {
            
        this.id = id;
        this.name = name;
        this.tags = tags;
    }
    
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }    
    
    public Iterable<String> getTags() {
        return tags;
    }
}
