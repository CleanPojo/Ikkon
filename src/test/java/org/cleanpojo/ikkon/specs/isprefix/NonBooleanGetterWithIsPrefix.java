package org.cleanpojo.ikkon.specs.isprefix;

public class NonBooleanGetterWithIsPrefix {

    private final String name;

    public NonBooleanGetterWithIsPrefix(final String name) {
        this.name = name;
    }

    public String isName() {
        return name;
    }
}
