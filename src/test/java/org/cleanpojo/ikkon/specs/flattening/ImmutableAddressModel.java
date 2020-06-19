package org.cleanpojo.ikkon.specs.flattening;

public class ImmutableAddressModel {

    private final String country;
    private final String state;
    private final String city;
    private final String zipCode;

    public ImmutableAddressModel(
        final String country,
        final String state,
        final String city,
        final String zipCode) {
            
        this.country = country;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }
    
    public String getState() {
        return state;
    }
    
    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }
}