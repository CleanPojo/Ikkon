package org.cleanpojo.ikkon.fixtures;

public class Freezable {
    
    private boolean frozen = false;

    public Freezable(boolean frozen) { this.frozen = frozen; }

    public boolean isFrozen() { return frozen; }

    public void freeze() { frozen = true; }
}
