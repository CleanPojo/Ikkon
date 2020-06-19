package org.cleanpojo.ikkon.specs.constructor;

import static org.assertj.core.api.Assertions.assertThat;

import org.cleanpojo.ikkon.Mapper;
import org.cleanpojo.ikkon.specs.Generator;
import org.junit.Test;

public class ConstructorSpecs {

    @Test
    public void supports_ConstructorProperties_annotation() {
        var source = Generator.create(Entity.class);
        var sut = new Mapper();

        var actual = sut.map(source, EntityWithConstructorProperties.class);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(source.getId());
        assertThat(actual.getName()).isEqualTo(source.getName());
    }
}
