package org.cleanpojo.ikkon.specs.collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.cleanpojo.ikkon.specs.Generator.create;

import org.cleanpojo.ikkon.Mapper;
import org.junit.Test;

public class CollectionSpecs {

    @Test
    public void correctly_maps_iterable_property_to_iterable_property_of_immutable_object() {
        // Arrange
        var source = create(ImmutableEntityWithIterableTags.class);
        var sut = new Mapper();

        // Act
        var actual = sut.map(source, ImmutableEntityWithIterableTags.class);

        // Assert
        assertThat(actual.getTags()).isNotSameAs(source.getTags());
        assertThat(actual.getTags()).isEqualTo(source.getTags());

        assertThat(actual.getId()).isEqualTo(source.getId());
        assertThat(actual.getName()).isEqualTo(source.getName());
    }    

    @Test
    public void correctly_maps_iterable_property_to_collection_property_of_immutable_object() {
        // Arrange
        var source = create(ImmutableEntityWithIterableTags.class);
        var sut = new Mapper();

        // Act
        var actual = sut.map(source, ImmutableEntityWithCollectionTags.class);

        // Assert
        assertThat(actual.getTags()).isNotSameAs(source.getTags());
        assertThat(actual.getTags()).isEqualTo(source.getTags());

        assertThat(actual.getId()).isEqualTo(source.getId());
        assertThat(actual.getName()).isEqualTo(source.getName());
    }

    @Test
    public void correctly_maps_iterable_property_to_list_property_of_immutable_object() {
        // Arrange
        var source = create(ImmutableEntityWithIterableTags.class);
        var sut = new Mapper();

        // Act
        var actual = sut.map(source, ImmutableEntityWithListTags.class);

        // Assert
        assertThat(actual.getTags()).isNotSameAs(source.getTags());
        assertThat(actual.getTags()).isEqualTo(source.getTags());

        assertThat(actual.getId()).isEqualTo(source.getId());
        assertThat(actual.getName()).isEqualTo(source.getName());
    }

    @Test
    public void correctly_maps_iterable_property_to_iterable_property_of_mutable_object() {
        // Arrange
        var source = create(ImmutableEntityWithIterableTags.class);
        var sut = new Mapper();

        // Act
        var actual = sut.map(source, MutableEntityWithIterableTags.class);

        // Assert
        assertThat(actual.getTags()).isNotSameAs(source.getTags());
        assertThat(actual.getTags()).isEqualTo(source.getTags());

        assertThat(actual.getId()).isEqualTo(source.getId());
        assertThat(actual.getName()).isEqualTo(source.getName());
    }
}
