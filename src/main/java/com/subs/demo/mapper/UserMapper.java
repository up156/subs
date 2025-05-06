package com.subs.demo.mapper;

import com.subs.demo.dto.UserDto;
import com.subs.demo.model.User;
import com.subs.demo.request.UserRequest;
import com.subs.demo.request.UserUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {SubMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    UserDto mapToUserDto(User user);

    User mapToUser(UserRequest request);

    void updateUser(@MappingTarget User toUpdate, UserUpdateRequest request);
}
