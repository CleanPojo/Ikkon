package org.cleanpojo.ikkon.specs.accessorconvention;

import java.util.UUID;

public class IsMethodWithParameter {

    private final UUID id;
    private final String name;

    public IsMethodWithParameter(final UUID id, final String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isFrozen(int queryParameter) {
        throw new RuntimeException("This method should not be called.");
    }
}
