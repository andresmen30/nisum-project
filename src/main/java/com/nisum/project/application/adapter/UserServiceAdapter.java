package com.nisum.project.application.adapter;

import com.nisum.project.application.api.UserService;
import com.nisum.project.domain.model.UserModel;
import com.nisum.project.domain.spi.UserPersistencePort;
import com.nisum.project.infrastructure.mappers.UserMapper;
import com.nisum.project.infrastructure.resource.user.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceAdapter implements UserService {

    private final UserPersistencePort userPersistencePort;


    @Autowired
    public UserServiceAdapter(final UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }


    public UserResponse create(final UserModel userModel) {
        return UserMapper.INSTANCE.mapToResponse(userPersistencePort.create(userModel));
    }


    public UserResponse update(final UserModel userModel, final Long idUser) {
        userModel.setUserId(idUser);
        return UserMapper.INSTANCE.mapToResponse(userPersistencePort.update(userModel, idUser));
    }

    public void delete(final Long idUser) {
        userPersistencePort.delete(idUser);
    }

    public List<UserModel> getAll() {
        return userPersistencePort.getAll();
    }

    public UserModel getById(final Long id) {
        return userPersistencePort.getById(id);
    }

    public boolean isExistByEmail(final String email) {
        return userPersistencePort.isExistByEmail(email);
    }

    public boolean isExistByIdAndEmail(final Long id, final String email) {
        return userPersistencePort.isExistByIdAndEmail(id, email);
    }

    public boolean isExistById(final Long userId) {
        return userPersistencePort.isExistById(userId);
    }

}
