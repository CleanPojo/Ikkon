package org.cleanpojo.ikkon.fixtures;

import java.util.UUID;

public class DeepImmutableComplexObject {

    private final UUID id;
    private final String name;
    private final ComplexObjectProperty child;

    public DeepImmutableComplexObject(
        final UUID id,
        final String name,
        final ComplexObjectProperty child) {
            
        this.id = id;
        this.name = name;
        this.child = child;
    }

    public UUID getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public ComplexObjectProperty getChild() {
        return child;
    }
}