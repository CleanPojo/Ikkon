package org.cleanpojo.ikkon;

import java.util.List;

final class CompositeGetterSelector implements GetterSelector {

    private final List<GetterSelector> selectors;

    public CompositeGetterSelector(GetterSelector... selectors) {
        this.selectors = List.of(selectors);
    }

    @Override
    public Getter select(Object source, PropertyDescriptor property) {
        for (GetterSelector selector : selectors) {
            Getter getter = selector.select(source, property);
            if (getter != null) {
                return getter;
            }
        }

        return null;
    }
}
