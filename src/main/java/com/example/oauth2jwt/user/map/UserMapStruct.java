package com.example.oauth2jwt.user.map;

import com.example.oauth2jwt.common.map.EntityMapStruct;
import com.example.oauth2jwt.user.dto.UserDto;
import com.example.oauth2jwt.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapStruct extends EntityMapStruct<UserDto, User> {
}
