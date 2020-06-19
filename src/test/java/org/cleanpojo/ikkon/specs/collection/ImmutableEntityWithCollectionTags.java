package org.cleanpojo.ikkon.specs.collection;

import java.util.Collection;
import java.util.UUID;

public class ImmutableEntityWithCollectionTags {

    private final UUID id;
    private final String name;
    private final Collection<String> tags;

    public ImmutableEntityWithCollectionTags(
        final UUID id,
        final String name,
        final Collection<String> tags) {
            
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
    
    public Collection<String> getTags() {
        return tags;
    }
}
