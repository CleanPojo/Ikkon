package org.cleanpojo.ikkon.specs.collection;

import java.util.List;
import java.util.UUID;

public class ImmutableEntityWithListTags {

    private final UUID id;
    private final String name;
    private final List<String> tags;

    public ImmutableEntityWithListTags(
        final UUID id,
        final String name,
        final List<String> tags) {
            
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
    
    public List<String> getTags() {
        return tags;
    }    
}
