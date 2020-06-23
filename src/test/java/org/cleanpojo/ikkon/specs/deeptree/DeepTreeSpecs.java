package org.cleanpojo.ikkon.specs.deeptree;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.cleanpojo.ikkon.Mapper;
import org.cleanpojo.ikkon.specs.Generator;
import org.junit.Test;

public class DeepTreeSpecs {

    @Test
    public void correctly_maps_complex_object_property() {
        // Arrange
        var source = Generator.create(Order.class);
        var sut = new Mapper();

        // Act
        var actual = sut.map(source, Order.class);

        // Assert
        assertThat(actual.getId()).isEqualTo(source.getId());

        assertThat(actual.getShippingAddress())
            .isNotSameAs(source.getShippingAddress())
            .usingRecursiveComparison()
            .isEqualTo(source.getShippingAddress());
    }

    @Test
    public void correctly_maps_null_value_of_complex_object_property() {
        var source = new Order(UUID.randomUUID(), null);
        var sut = new Mapper();

        var actual = sut.map(source, Order.class);

        assertThat(actual.getShippingAddress()).isNull();
    }
}
