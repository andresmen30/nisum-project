package com.nisum.project.domain.spi;

import com.nisum.project.domain.model.UserModel;

import java.util.List;

public interface UserPersistencePort {


    UserModel create(final UserModel userModel);

    UserModel update(final UserModel userModel, final Long idUser);

    void delete(final Long idUser);

    List<UserModel> getAll();

    UserModel getById(final Long id);

    boolean isExistByEmail(final String email);

    boolean isExistByIdAndEmail(final Long id, final String email);

    boolean isExistById(final Long userId);

}
