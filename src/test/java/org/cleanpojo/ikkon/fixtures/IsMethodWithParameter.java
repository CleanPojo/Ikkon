package org.cleanpojo.ikkon.fixtures;

public class IsMethodWithParameter {

    private boolean frozen = false;

    public IsMethodWithParameter(boolean frozen) { this.frozen = frozen; }

    public boolean isFrozen(int queryParameter) { return frozen; }

    public void freeze() { frozen = true; }
}
