package org.cleanpojo.ikkon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.cleanpojo.ikkon.fixtures.Generator.create;

import org.cleanpojo.ikkon.fixtures.*;
import org.junit.Test;

public class Mapper_specs {

    @Test
    public void correctly_maps_immutable_object_to_mutable_object() {
        var source = create(Immutable.class);
        var sut = new Mapper();

        Mutable actual = sut.map(source, Mutable.class);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(source.getId());
        assertThat(actual.getName()).isEqualTo(source.getName());
    }

    @Test
    public void correctly_maps_mutable_object_to_immutable_object() {
        var source = create(Mutable.class);
        var sut = new Mapper();

        Immutable actual = sut.map(source, Immutable.class);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(source.getId());
        assertThat(actual.getName()).isEqualTo(source.getName());
    }

    @Test
    public void fails_if_destination_type_has_multiple_constructors() {
        var source = create(Mutable.class);
        var sut = new Mapper();

        Throwable thrown = catchThrowable(() -> sut.map(source, MultipleConstructors.class));

        assertThat(thrown)
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining(MultipleConstructors.class.getName());
    }

    @Test
    public void correctly_maps_iterable_property() {
        var source = create(ImmutableIterableProperty.class);
        var sut = new Mapper();

        var actual = sut.map(source, ImmutableIterableProperty.class);

        assertThat(actual.getValues()).isNotSameAs(source.getValues());
        assertThat(actual.getValues()).isEqualTo(source.getValues());
    }
}
