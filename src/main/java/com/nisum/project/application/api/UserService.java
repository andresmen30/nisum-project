package com.nisum.project.application.api;


import com.nisum.project.domain.model.UserModel;
import com.nisum.project.infrastructure.resource.user.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse create(final UserModel userModel);

    UserResponse update(final UserModel userModel, final Long idUser);

    void delete(final Long idUser);

    List<UserModel> getAll();

    UserModel getById(final Long id);

    boolean isExistByEmail(final String email);

    boolean isExistByIdAndEmail(final Long id, final String email);

    boolean isExistById(final Long userId);


}
