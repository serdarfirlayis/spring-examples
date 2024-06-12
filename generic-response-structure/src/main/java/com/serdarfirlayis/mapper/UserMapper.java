package com.serdarfirlayis.mapper;

import com.serdarfirlayis.entity.UserEntity;
import com.serdarfirlayis.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User UserEntityToUser(UserEntity userEntity);
}
