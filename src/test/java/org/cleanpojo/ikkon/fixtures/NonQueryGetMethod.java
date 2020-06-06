package org.cleanpojo.ikkon.fixtures;

import java.util.UUID;

public class NonQueryGetMethod {

    private final UUID id;

    public NonQueryGetMethod(final UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void getName() {
        throw new RuntimeException("This method should not be called.");
    }
}
