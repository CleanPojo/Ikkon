package org.cleanpojo.ikkon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.cleanpojo.ikkon.fixtures.Generator.create;

import org.cleanpojo.ikkon.fixtures.ImmutableObject;
import org.cleanpojo.ikkon.fixtures.MutableObject;
import org.junit.Test;

public class Mapper_specs {

    @Test
    public void correctly_maps_immutable_object_to_mutable_object() {
        var source = create(ImmutableObject.class);
        var sut = new Mapper();

        MutableObject actual = sut.map(source, MutableObject.class);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(source.getId());
        assertThat(actual.getName()).isEqualTo(source.getName());
    }

    @Test
    public void correctly_maps_mutable_object_to_immutable_object() {
        var source = create(MutableObject.class);
        var sut = new Mapper();

        ImmutableObject actual = sut.map(source, ImmutableObject.class);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(source.getId());
        assertThat(actual.getName()).isEqualTo(source.getName());
    }
}
