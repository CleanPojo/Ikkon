package org.cleanpojo.ikkon;

interface GetterSelector {

    Getter select(PropertyDescriptor property, Class<?> source);

    static GetterSelector instance = new CompositeGetterSelector(
        new SimpleGetterSelector(),
        new FlatteningGetterSelector(),
        new UnflatteningGetterSelector()
    );
}
