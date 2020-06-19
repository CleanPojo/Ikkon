package org.cleanpojo.ikkon.specs.deeptree;

import static org.assertj.core.api.Assertions.assertThat;

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
}
