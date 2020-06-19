package org.cleanpojo.ikkon.specs.defaultvalue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.cleanpojo.ikkon.specs.Generator.create;

import org.cleanpojo.ikkon.Mapper;
import org.junit.Test;

public class DefaultValueSpecs {

    @Test
    public void sets_unprovided_byte_type_parameter_to_default() {
        var source = create(Entity.class);
        var sut = new Mapper();

        var actual = sut.map(source, EntityWithByteValue.class);

        assertThat(actual.getValue()).isEqualTo((byte)0);
    }

    @Test
    public void sets_unprovided_short_type_parameter_to_default() {
        var source = create(Entity.class);
        var sut = new Mapper();

        var actual = sut.map(source, EntityWithShortValue.class);

        assertThat(actual.getValue()).isEqualTo((short)0);
    }

    @Test
    public void sets_unprovided_int_type_parameter_to_default() {
        var source = create(Entity.class);
        var sut = new Mapper();

        var actual = sut.map(source, EntityWithIntValue.class);

        assertThat(actual.getValue()).isEqualTo(0);
    }

    @Test
    public void sets_unprovided_long_type_parameter_to_default() {
        var source = create(Entity.class);
        var sut = new Mapper();

        var actual = sut.map(source, EntityWithLongValue.class);

        assertThat(actual.getValue()).isEqualTo((long)0);
    }

    @Test
    public void sets_unprovided_double_type_parameter_to_default() {
        var source = create(Entity.class);
        var sut = new Mapper();

        var actual = sut.map(source, EntityWithDoubleValue.class);

        assertThat(actual.getValue()).isEqualTo((double)0);
    }

    @Test
    public void sets_unprovided_float_type_parameter_to_default() {
        var source = create(Entity.class);
        var sut = new Mapper();

        var actual = sut.map(source, EntityWithFloatValue.class);

        assertThat(actual.getValue()).isEqualTo((float)0);
    }

    @Test
    public void sets_unprovided_char_type_parameter_to_default() {
        var source = create(Entity.class);
        var sut = new Mapper();

        var actual = sut.map(source, EntityWithCharValue.class);

        assertThat(actual.getValue()).isEqualTo('\u0000');
    }
}
