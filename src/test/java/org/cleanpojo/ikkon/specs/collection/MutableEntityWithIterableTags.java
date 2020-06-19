package org.cleanpojo.ikkon.specs.collection;

import java.util.UUID;

public class MutableEntityWithIterableTags {

    private UUID id;
    private String name;
    private Iterable<String> tags;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Iterable<String> getTags() {
        return tags;
    }

    public void setTags(Iterable<String> tags) {
        this.tags = tags;
    }
}
