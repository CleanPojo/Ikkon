package org.cleanpojo.ikkon.fixtures;

import java.util.UUID;

public class MutableIterableProperty {

    private UUID id;
    private String name;
    private Iterable<String> values;

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

    public Iterable<String> getValues() {
        return values;
    }

    public void setValues(Iterable<String> values) {
        this.values = values;
    }
}
