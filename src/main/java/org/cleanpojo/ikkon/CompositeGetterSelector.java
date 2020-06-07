package org.cleanpojo.ikkon;

import java.util.List;

final class CompositeGetterSelector implements GetterSelector {

    private final List<GetterSelector> selectors;

    public CompositeGetterSelector(GetterSelector... selectors) {
        this.selectors = List.of(selectors);
    }

    @Override
    public Getter select(PropertyDescriptor property, Class<?> source) {
        for (GetterSelector selector : selectors) {
            Getter getter = selector.select(property, source);
            if (getter != null) {
                return getter;
            }
        }

        return null;
    }
}
