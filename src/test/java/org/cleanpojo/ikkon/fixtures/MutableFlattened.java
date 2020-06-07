package org.cleanpojo.ikkon.fixtures;

import java.util.UUID;

public class MutableFlattened {

    private UUID childId;
    private String childName;

    public UUID getChildId() {
        return childId;
    }

    public void setChildId(UUID childId) {
        this.childId = childId;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }
}