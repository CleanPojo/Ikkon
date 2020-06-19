package org.cleanpojo.ikkon.specs.accessorconvention;

import java.util.UUID;

public class ParameterlessSetMethod {

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

    public void setName() {
        this.name = "Obiwan Kenobi";
        throw new RuntimeException("This method should not be called.");
    }
}
