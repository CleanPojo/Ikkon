package org.cleanpojo.ikkon.fixtures;

import java.util.UUID;

public class GetMethodWithParameter {

    private final UUID id;
    private final String name;

    public GetMethodWithParameter(final UUID id, final String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName(int queryParameter) {
        return name;
    }
}
