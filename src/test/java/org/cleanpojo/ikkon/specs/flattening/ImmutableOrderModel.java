package org.cleanpojo.ikkon.specs.flattening;

import java.util.UUID;

public class ImmutableOrderModel {

    private final UUID id;
    private final ImmutableAddressModel shippingAddress;

    public ImmutableOrderModel(
        final UUID id,
        final ImmutableAddressModel shippingAddress) {

        this.id = id;
        this.shippingAddress = shippingAddress;
    }
    
    public UUID getId() {
        return id;
    }

    public ImmutableAddressModel getShippingAddress() {
        return shippingAddress;
    }
}
