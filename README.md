# Ikkon

Ikkon is convention based object mapping tool.

![CI](https://github.com/CleanPojo/Ikkon/workflows/CI/badge.svg) ![Publish](https://github.com/CleanPojo/Ikkon/workflows/Publish/badge.svg)

> [Images of Ikkon](https://media.comicbook.com/2018/08/images-of-ikonn-1128198.jpeg)

> Please refer to [this issue](https://github.com/redhat-developer/vscode-java/issues/666) to pass all tests.

---

## Install

### Maven

```xml
<dependency>
  <groupId>io.github.cleanpojo</groupId>
  <artifactId>ikkon</artifactId>
  <version>0.0.2</version>
</dependency>
```

### Gradle

```groovy
implementation 'io.github.cleanpojo:ikkon:0.0.2'
```

---

## Features

### Map to mutable object

```java
public class ImmutableEntity {

    private final UUID id;
    private final String name;

    public ImmutableEntity(final UUID id, final String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

public class MutableEntity {

    private UUID id;
    private String name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

var source = new ImmutableEntity(UUID.randomUUID(), "foo");

var destination = new Mapper().map(source, MutableEntity.class);
```

### Map to immutable object

`-parameters` compiler option is required in order to map to immutable object in convention based manner.

```java
public class MutableEntity {

    private UUID id;
    private String name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class ImmutableEntity {

    private final UUID id;
    private final String name;

    public ImmutableEntity(final UUID id, final String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

var source = new MutableEntity();
source.setId(UUID.randomUUID());
source.setName("foo");

var destination = new Mapper().map(source, ImmutableEntity.class);
```

`@ConstructorProperties` annotation can be used to specify property-corresponding parameters instead of `-parameter` compiler option.

```java
public class ImmutableEntity {

    private final UUID id;
    private final String name;

    @ConstructorProperties({ "id", "name" })
    public ImmutableEntity(final UUID arg1, final String arg2) {
        this.id = arg1;
        this.name = arg2;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
```

### Map complex object properties

`Mapper` maps both of simple object properties and complex object properties.

```java
public class Address {

    private final String country;
    private final String state;
    private final String city;
    private final String zipCode;

    public Address(
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

var source = new Order(
    UUID.randomUUID(),
    new Address(
        "Country",
        "State",
        "City",
        "65535",
    )
);

var destination = new Mapper().map(source, Order.class);
```

### Flatten complex object to simple values

`Mapper` flattens a complex object property to simple value properties.

```java
public class Address {

    private final String country;
    private final String state;
    private final String city;
    private final String zipCode;

    public Address(
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

public class Payment {

    private final UUID id;
    private final String paymentMethod;
    private final Order order;

    public Payment(
        final UUID id,
        final String paymentMethod,
        final Order order) {

        this.id = id;
        this.paymentMethod = paymentMethod;
        this.order = order;
    }

    public UUID getId() {
        return id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Order getOrder() {
        return order;
    }
}

public class PaymentRecord {

    private final UUID id;
    private final String paymentMethod;
    private final UUID orderId;
    private final String orderShippingAddressCountry;
    private final String orderShippingAddressState;
    private final String orderShippingAddressCity;
    private final String orderShippingAddressZipCode;

    public PaymentRecord(
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

var source = new Payment(
    UUID.randomUUID(),
    "Credit Card",
    new Order(
        UUID.randomUUID(),
        new Address(
            "Country",
            "State",
            "City",
            "65535",
        )
    )
);

var destination = new Mapper().map(source, PaymentRecord.class);
```

`Mapper` also unflattens simple value properties to a complex object property.

```java
var source = new PaymentRecord(
    UUID.randomUUID(),
    "Credit Card",
    UUID.randomUUID(),
    "Country",
    "State",
    "City",
    "65535"
);

var destination = new Mapper().map(source, Payment.class);
```
