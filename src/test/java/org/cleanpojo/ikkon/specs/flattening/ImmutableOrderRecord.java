package org.cleanpojo.ikkon.specs.flattening;

import java.util.UUID;

public class ImmutableOrderRecord {

    private final UUID id;
    private final String shippingAddressCountry;
    private final String shippingAddressState;
    private final String shippingAddressCity;
    private final String shippingAddressZipCode;

    public ImmutableOrderRecord(
        final UUID id,
        final String shippingAddressCountry,
        final String shippingAddressState,
        final String shippingAddressCity,
        final String shippingAddressZipCode) {
            
        this.id = id;
        this.shippingAddressCountry = shippingAddressCountry;
        this.shippingAddressState = shippingAddressState;
        this.shippingAddressCity = shippingAddressCity;
        this.shippingAddressZipCode = shippingAddressZipCode;
    }

    public UUID getId() {
        return id;
    }

    public String getShippingAddressCountry() {
        return shippingAddressCountry;
    }

    public String getShippingAddressState() {
        return shippingAddressState;
    }

    public String getShippingAddressCity() {
        return shippingAddressCity;
    }

    public String getShippingAddressZipCode() {
        return shippingAddressZipCode;
    }
}
