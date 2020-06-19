package org.cleanpojo.ikkon.specs.accessorconvention;

import java.util.UUID;

public class VoidGetMethod {

    private final UUID id;

    public VoidGetMethod(final UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void getName() {
        throw new RuntimeException("This method should not be called.");
    }
}
