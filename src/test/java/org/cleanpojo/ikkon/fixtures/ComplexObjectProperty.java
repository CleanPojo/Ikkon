package org.cleanpojo.ikkon.fixtures;

public class ComplexObjectProperty {

    private final Immutable child;

    public ComplexObjectProperty(final Immutable child) {
        this.child = child;
    }

    public Immutable getChild() {
        return child;
    }
}
