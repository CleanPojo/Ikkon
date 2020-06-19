package org.cleanpojo.ikkon.specs.accessorconvention;

import java.util.UUID;

public class QuerySetMethod {

    private UUID id;
    private String name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        this.name = name;
        return name;
    }
}
