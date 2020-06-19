package org.cleanpojo.ikkon.specs.accessorconvention;

import java.util.UUID;

public class FreezableEntity extends Entity {
    
    private boolean frozen = false;

    public FreezableEntity(UUID id, String name, boolean frozen) {
        super(id, name);
        this.frozen = frozen;
    }

    public boolean isFrozen() { return frozen; }

    public void freeze() { frozen = true; }
}
