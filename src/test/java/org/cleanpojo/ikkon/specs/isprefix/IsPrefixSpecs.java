package org.cleanpojo.ikkon.specs.isprefix;

import static org.assertj.core.api.Assertions.assertThat;
import static org.cleanpojo.ikkon.specs.Generator.create;

import org.cleanpojo.ikkon.Mapper;
import org.junit.Test;

public class IsPrefixSpecs {

    @Test
    public void supports_is_prefix_for_boolean_property() {
        var source = create(Freezable.class);
        var sut = new Mapper();

        var actual = sut.map(source, Freezable.class);

        assertThat(actual.isFrozen()).isEqualTo(source.isFrozen());
    }

    @Test
    public void ignores_method_decorated_with_invalid_is_prefix() {
        var source = create(NonBooleanGetterWithIsPrefix.class);
        var sut = new Mapper();

        var actual = sut.map(source, NonBooleanGetterWithIsPrefix.class);

        assertThat(actual.isName()).isNull();
    }
}
