package com.subs.demo.mapper;

import com.subs.demo.dto.SubDto;
import com.subs.demo.model.Sub;
import com.subs.demo.request.SubRequest;
import com.subs.demo.request.SubUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SubMapper {

    SubDto mapToSubDto(Sub sub);

    Sub mapToSub(SubRequest request);

    void updateSub(@MappingTarget Sub toUpdate, SubUpdateRequest request);
}
