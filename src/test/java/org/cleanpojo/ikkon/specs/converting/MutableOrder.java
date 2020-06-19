package org.cleanpojo.ikkon.specs.converting;

import java.util.UUID;

public class MutableOrder {

    private UUID id;
    private MutableAddress shippingAddress;
    
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }

    public MutableAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(MutableAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
