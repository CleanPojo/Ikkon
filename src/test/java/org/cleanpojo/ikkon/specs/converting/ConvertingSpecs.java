package org.cleanpojo.ikkon.specs.converting;

import static org.assertj.core.api.Assertions.assertThat;

import org.cleanpojo.ikkon.Mapper;
import org.cleanpojo.ikkon.specs.Generator;
import org.junit.Test;

public class ConvertingSpecs {

    @Test
    public void correctly_converts_property_to_destination_type() {
        var source = Generator.create(ImmutableOrder.class);
        var sut = new Mapper();

        var actual = sut.map(source, MutableOrder.class);

        assertThat(actual).usingRecursiveComparison().isEqualTo(source);
    }
}
