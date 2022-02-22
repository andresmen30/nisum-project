package com.nisum.project.infrastructure.mappers;

import com.nisum.project.domain.model.UserModel;
import com.nisum.project.infrastructure.entity.User;
import com.nisum.project.infrastructure.resource.user.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Mapper(imports = {LocalDateTime.class, UUID.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "created", expression = "java(userModel.getUserId() == null ? LocalDateTime.now() : userModel.getCreated())")
    @Mapping(target = "modified", expression = "java(userModel.getUserId() != null ? LocalDateTime.now() : userModel.getModified())")
    @Mapping(target = "lastLogin", expression = "java(userModel.getLastLogin() == null ? user.getCreated() : userModel.getLastLogin())")
    @Mapping(target = "token", expression = "java(userModel.getUserId() == null ? UUID.randomUUID() : userModel.getToken())")
    User mapToEntity(final UserModel userModel);

    UserModel mapToModel(final User user);

    List<UserModel> mapToEntity(final List<User> list);


    UserResponse mapToResponse(final UserModel userModel);

}
