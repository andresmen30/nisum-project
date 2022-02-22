package com.nisum.project.infrastructure.adapter;

import com.nisum.project.domain.model.UserModel;
import com.nisum.project.domain.spi.UserPersistencePort;
import com.nisum.project.infrastructure.entity.User;
import com.nisum.project.infrastructure.mappers.UserMapper;
import com.nisum.project.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPersistenceAdapter implements UserPersistencePort {

    private final UserRepository userRepository;

    @Autowired
    public UserPersistenceAdapter(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserModel create(final UserModel userModel) {
        final User user = UserMapper.INSTANCE.mapToEntity(userModel);
        user.setActive(true);
        return UserMapper.INSTANCE.mapToModel(userRepository.save(user));
    }

    public UserModel update(final UserModel userModel, final Long idUser) {
        final User user = UserMapper.INSTANCE.mapToEntity(userModel);
        user.setActive(true);
        return UserMapper.INSTANCE.mapToModel(userRepository.save(user));
    }

    public void delete(final Long idUser) {
        userRepository.deleteById(idUser);
    }

    public List<UserModel> getAll() {
        return UserMapper.INSTANCE.mapToEntity(userRepository.findAll());
    }

    public UserModel getById(final Long id) {
        return UserMapper.INSTANCE.mapToModel(userRepository.findById(id).orElse(null));
    }

    public boolean isExistByEmail(final String email) {
        return userRepository.userByEmail(email).isPresent();
    }

    public boolean isExistByIdAndEmail(final Long id, final String email) {
        return userRepository.isExistByIdAndEmail(id, email).isPresent();
    }

    public boolean isExistById(final Long userId) {
        return userRepository.existsById(userId);
    }


}
