package org.cleanpojo.ikkon.specs.accessorconvention;

import static org.assertj.core.api.Assertions.assertThat;
import static org.cleanpojo.ikkon.specs.Generator.create;

import org.cleanpojo.ikkon.Mapper;
import org.junit.Test;

public class AccessorConventionSpecs {

    @Test
    public void excepts_non_query_get_methods() {
        var source = create(VoidGetMethod.class);
        var sut = new Mapper();

        var actual = sut.map(source, Entity.class);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(source.getId());
        assertThat(actual.getName()).isNull();
    }

    @Test
    public void excepts_get_query_methods_with_parameters() {
        var source = create(GetMethodWithParameter.class);
        var sut = new Mapper();

        var actual = sut.map(source, Entity.class);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(source.getId());
        assertThat(actual.getName()).isNull();
    }
    
    @Test
    public void excepts_is_query_methods_with_parameters() {
        var source = create(IsMethodWithParameter.class);
        var sut = new Mapper();
        
        var actual = sut.map(source, FreezableEntity.class);
        
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(source.getId());
        assertThat(actual.getName()).isEqualTo(source.getName());
        assertThat(actual.isFrozen()).isFalse();
    }

    @Test
    public void excepts_query_method_with_set_prefix() {
        var source = create(Entity.class);
        var sut = new Mapper();

        var actual = sut.map(source, QuerySetMethod.class);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(source.getId());
        assertThat(actual.getName()).isNull();
    }

    @Test
    public void excepts_parameterless_set_method() {
        var source = create(Entity.class);
        var sut = new Mapper();

        var actual = sut.map(source, ParameterlessSetMethod.class);

        assertThat(actual.getName()).isNull();
    }
}
