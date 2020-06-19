package org.cleanpojo.ikkon.specs.accessorconvention;

import java.util.UUID;

public class GetMethodWithParameter {

    private final UUID id;

    public GetMethodWithParameter(final UUID id) {
        this.id = id;
    }
    
    public UUID getId() {
        return id;
    }

    public String getName(int queryParameter) {
        throw new RuntimeException("This method should not be called.");
    }
}
