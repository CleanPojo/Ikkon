package org.cleanpojo.ikkon.specs.basic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.cleanpojo.ikkon.specs.Generator.create;

import org.cleanpojo.ikkon.Mapper;
import org.junit.Test;

public class BasicSpecs {

    @Test
    public void correctly_maps_immutable_object_to_mutable_object() {
        var source = create(ImmutableEntity.class);
        var sut = new Mapper();

        var actual = sut.map(source, MutableEntity.class);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(source.getId());
        assertThat(actual.getName()).isEqualTo(source.getName());
    }    

    @Test
    public void correctly_maps_mutable_object_to_immutable_object() {
        var source = create(MutableEntity.class);
        var sut = new Mapper();

        var actual = sut.map(source, ImmutableEntity.class);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(source.getId());
        assertThat(actual.getName()).isEqualTo(source.getName());
    }

    @Test
    public void fails_if_destination_type_has_multiple_constructors() {
        var source = create(MutableEntity.class);
        var sut = new Mapper();

        Throwable thrown = catchThrowable(
            () -> sut.map(source, EntityWithMultipleConstructor.class));

        assertThat(thrown)
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining(EntityWithMultipleConstructor.class.getName());
    }
}
