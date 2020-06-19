package org.cleanpojo.ikkon.specs.converting;

import java.util.UUID;

public class ImmutableOrder {

    private final UUID id;
    private final ImmutableAddress shippingAddress;

    public ImmutableOrder(
        final UUID id,
        final ImmutableAddress shippingAddress) {

        this.id = id;
        this.shippingAddress = shippingAddress;
    }
    
    public UUID getId() {
        return id;
    }

    public ImmutableAddress getShippingAddress() {
        return shippingAddress;
    }
}
