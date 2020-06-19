package org.cleanpojo.ikkon.specs.flattening;

import java.util.UUID;

public class MutableOrderModel {

    private UUID id;
    private ImmutableAddressModel shippingAddress;
    
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }

    public ImmutableAddressModel getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ImmutableAddressModel shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
