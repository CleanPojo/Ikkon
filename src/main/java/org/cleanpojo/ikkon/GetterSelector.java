package org.cleanpojo.ikkon;

interface GetterSelector {

    Getter select(Object source, PropertyHint property);

    static GetterSelector instance = new CompositeGetterSelector(
        new SimpleGetterSelector(),
        new FlatteningGetterSelector(),
        new UnflatteningGetterSelector()
    );
}
