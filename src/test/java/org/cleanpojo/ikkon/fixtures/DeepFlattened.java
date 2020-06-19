package org.cleanpojo.ikkon.fixtures;

import java.util.UUID;

public class DeepFlattened {

    private UUID id;
    private String name;
    private UUID childChildId;
    private String childChildName;

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
    
    public UUID getChildChildId() {
        return childChildId;
    }
    
    public void setChildChildId(UUID childChildId) {
        this.childChildId = childChildId;
    }

    public String getChildChildName() {
        return childChildName;
    }

    public void setChildChildName(String childChildName) {
        this.childChildName = childChildName;
    }
}
