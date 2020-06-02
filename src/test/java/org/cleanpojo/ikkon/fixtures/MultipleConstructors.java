package org.cleanpojo.ikkon.fixtures;

import java.util.UUID;

public class MultipleConstructors {

    private UUID id;
    private String name;

    public MultipleConstructors() {
    }

    public MultipleConstructors(UUID id, String name) {
        setId(id);
        setName(name);
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
