package org.cleanpojo.ikkon.specs.flattening;

import java.util.UUID;

public class MutablePaymentRecord {
    
    private UUID id;
    private String paymentMethod;
    private UUID orderId;
    private String orderShippingAddressCountry;
    private String orderShippingAddressState;
    private String orderShippingAddressCity;
    private String orderShippingAddressZipCode;
    
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

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public String getOrderShippingAddressCountry() {
        return orderShippingAddressCountry;
    }

    public void setOrderShippingAddressCountry(String orderShippingAddressCountry) {
        this.orderShippingAddressCountry = orderShippingAddressCountry;
    }

    public String getOrderShippingAddressState() {
        return orderShippingAddressState;
    }

    public void setOrderShippingAddressState(String orderShippingAddressState) {
        this.orderShippingAddressState = orderShippingAddressState;
    }

    public String getOrderShippingAddressCity() {
        return orderShippingAddressCity;
    }

    public void setOrderShippingAddressCity(String orderShippingAddressCity) {
        this.orderShippingAddressCity = orderShippingAddressCity;
    }

    public String getOrderShippingAddressZipCode() {
        return orderShippingAddressZipCode;
    }

    public void setOrderShippingAddressZipCode(String orderShippingAddressZipCode) {
        this.orderShippingAddressZipCode = orderShippingAddressZipCode;
    }
}
