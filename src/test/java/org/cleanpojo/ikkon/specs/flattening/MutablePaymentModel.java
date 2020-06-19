package org.cleanpojo.ikkon.specs.flattening;

import java.util.UUID;

public class MutablePaymentModel {

    private UUID id;
    private String paymentMethod;
    private ImmutableOrderModel order;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public ImmutableOrderModel getOrder() {
        return order;
    }

    public void setOrder(ImmutableOrderModel order) {
        this.order = order;
    }
}
