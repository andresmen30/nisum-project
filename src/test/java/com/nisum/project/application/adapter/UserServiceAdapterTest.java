package com.nisum.project.application.adapter;

import com.nisum.project.domain.model.PhoneModel;
import com.nisum.project.domain.model.UserModel;
import com.nisum.project.domain.port.UserPersistencePort;
import com.nisum.project.application.resource.user.response.UserResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
class UserServiceAdapterTest {

    private UserPersistencePort userPersistencePort = Mockito.mock(UserPersistencePort.class);
    private UserServiceAdapter userServiceAdapter = new UserServiceAdapter(userPersistencePort);

    @BeforeEach
    void setUp() {
        final List<UserModel> users = getUsersModel();
        Mockito.when(userPersistencePort.getAll()).thenReturn(users);
        Mockito.when(userPersistencePort.getById(NumberUtils.LONG_ZERO))
                .thenReturn(users.get(NumberUtils.INTEGER_ZERO));
        Mockito.when(userPersistencePort.isExistByEmail(users.get(NumberUtils.INTEGER_ZERO)
                .getEmail())).thenReturn(true);
        Mockito.when(userPersistencePort.isExistById(users.get(NumberUtils.INTEGER_ZERO)
                .getUserId())).thenReturn(true);
        Mockito.when(userPersistencePort.isExistByIdAndEmail(users.get(NumberUtils.INTEGER_ZERO)
                .getUserId(), users.get(NumberUtils.INTEGER_ZERO)
                .getEmail())).thenReturn(true);
        Mockito.when(userPersistencePort.create(users.get(NumberUtils.INTEGER_ZERO)))
                .thenReturn(users.get(NumberUtils.INTEGER_ZERO));
        Mockito.when(userPersistencePort.update(users.get(NumberUtils.INTEGER_ZERO), NumberUtils.LONG_ZERO))
                .thenReturn(users.get(NumberUtils.INTEGER_ZERO));
    }

    @Test
    void create() {
        final UserResponse userResponse = userServiceAdapter
                .create(getUsersModel().get(NumberUtils.INTEGER_ZERO));
        Assertions.assertTrue(userResponse == null);
    }

    @Test
    void update() {
        final UserResponse userResponse = userServiceAdapter
                .update(getUsersModel().get(NumberUtils.INTEGER_ZERO), NumberUtils.LONG_ZERO);
        Assertions.assertTrue(userResponse == null);
    }

    @Test
    void delete() {

    }

    @Test
    void getAll() {
        final List<UserModel> users = userServiceAdapter.getAll();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(users));
    }

    @Test
    void getById() {
        final UserModel userModel = userServiceAdapter.getById(NumberUtils.LONG_ZERO);
        Assertions.assertTrue(userModel != null);
    }

    @Test
    void isExistByEmail() {
        Assertions.assertTrue(userServiceAdapter.isExistByEmail(getUsersModel()
                .get(NumberUtils.INTEGER_ZERO).getEmail()));
    }

    @Test
    void isExistByIdAndEmail() {
        final UserModel userModel = getUsersModel()
                .get(NumberUtils.INTEGER_ZERO);
        Assertions.assertTrue(userServiceAdapter.isExistByIdAndEmail(userModel.getUserId(),
                userModel.getEmail()));
    }

    @Test
    void isExistById() {
        Assertions.assertTrue(userServiceAdapter.isExistById(getUsersModel()
                .get(NumberUtils.INTEGER_ZERO).getUserId()));
    }


    private List<UserModel> getUsersModel() {
        final List<UserModel> usersModel = new ArrayList<>();
        final UserModel userModel = new UserModel();
        userModel.setUserId(NumberUtils.LONG_ONE);
        userModel.setEmail("nisum@gmail.com");
        userModel.setActive(true);
        userModel.setPassword("123");
        userModel.setName("nisum nombre");
        userModel.setToken(UUID.randomUUID());
        userModel.setPhones(getPhonesModel());
        userModel.setModified(LocalDateTime.now());
        userModel.setLastLogin(LocalDateTime.now());
        userModel.setCreated(LocalDateTime.now());
        usersModel.add(userModel);
        return usersModel;
    }

    private List<PhoneModel> getPhonesModel() {
        final PhoneModel phoneModel = new PhoneModel();
        final List<PhoneModel> phoneModels = new ArrayList<>();
        phoneModel.setPhoneId(NumberUtils.LONG_ONE);
        phoneModel.setCityCode("01");
        phoneModel.setCountryCode("57");
        phoneModel.setCountryCode("10");
        phoneModel.setNumber("3108873112");
        phoneModels.add(phoneModel);
        return phoneModels;
    }
}