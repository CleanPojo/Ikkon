package org.cleanpojo.ikkon.specs.deeptree;

import java.util.UUID;

public class Order {

    private final UUID id;
    private final Address shippingAddress;

    public Order(
        final UUID id,
        final Address shippingAddress) {

        this.id = id;
        this.shippingAddress = shippingAddress;
    }
    
    public UUID getId() {
        return id;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }
}
