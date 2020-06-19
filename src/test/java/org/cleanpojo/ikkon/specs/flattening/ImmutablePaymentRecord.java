package org.cleanpojo.ikkon.specs.flattening;

import java.util.UUID;

public class ImmutablePaymentRecord {

    private final UUID id;
    private final String paymentMethod;
    private final UUID orderId;
    private final String orderShippingAddressCountry;
    private final String orderShippingAddressState;
    private final String orderShippingAddressCity;
    private final String orderShippingAddressZipCode;

    public ImmutablePaymentRecord(
            final UUID id,
            final String paymentMethod,
            final UUID orderId,
            final String orderShippingAddressCountry,
            final String orderShippingAddressState,
            final String orderShippingAddressCity,
            final String orderShippingAddressZipCode) {
                
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.orderId = orderId;
        this.orderShippingAddressCountry = orderShippingAddressCountry;
        this.orderShippingAddressState = orderShippingAddressState;
        this.orderShippingAddressCity = orderShippingAddressCity;
        this.orderShippingAddressZipCode = orderShippingAddressZipCode;
    }

    public UUID getId() {
        return id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public String getOrderShippingAddressCountry() {
        return orderShippingAddressCountry;
    }

    public String getOrderShippingAddressState() {
        return orderShippingAddressState;
    }

    public String getOrderShippingAddressCity() {
        return orderShippingAddressCity;
    }

    public String getOrderShippingAddressZipCode() {
        return orderShippingAddressZipCode;
    }
}
