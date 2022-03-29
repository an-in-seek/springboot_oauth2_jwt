package com.example.oauth2jwt.common.map;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

public interface EntityMapStruct<D, E> {

    // DTO -> Entity 매핑
    E toEntity(D dto);

    // Entity -> DTO 매핑
    D toDto(E entity);

    // DTO의 값이 들어있는 데이터들 기준으로 -> Entity에 덮어씌우기
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(D dto, @MappingTarget E entity);
}