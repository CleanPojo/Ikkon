package org.cleanpojo.ikkon.specs.basic;

import java.util.UUID;

public class EntityWithMultipleConstructor {

    private UUID id;
    private String name;

    public EntityWithMultipleConstructor() {
    }

    public EntityWithMultipleConstructor(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

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
}
