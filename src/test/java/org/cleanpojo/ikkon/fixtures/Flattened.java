package org.cleanpojo.ikkon.fixtures;

import java.util.UUID;

public class Flattened {

    private final UUID childId;
    private final String childName;

    public Flattened(final UUID childId, final String childName) {
        this.childId = childId;
        this.childName = childName;
    }

    public UUID getChildId() {
        return childId;
    }

    public String getChildName() {
        return childName;
    }
}
