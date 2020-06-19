package org.cleanpojo.ikkon.specs.flattening;

import java.util.UUID;

public class ImmutablePaymentModel {

    private final UUID id;
    private final String paymentMethod;
    private final ImmutableOrderModel order;

    public ImmutablePaymentModel(
        final UUID id,
        final String paymentMethod,
        final ImmutableOrderModel order) {
            
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.order = order;
    }

    public UUID getId() {
        return id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public ImmutableOrderModel getOrder() {
        return order;
    }
}
