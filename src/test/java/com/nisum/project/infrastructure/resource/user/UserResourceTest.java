package com.nisum.project.infrastructure.resource.user;

import com.nisum.project.application.api.UserService;
import com.nisum.project.domain.model.PhoneModel;
import com.nisum.project.domain.model.UserModel;
import com.nisum.project.infrastructure.resource.user.response.UserResponse;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
class UserResourceTest {

    private UserService userService = Mockito.mock(UserService.class);
    private UserResource userResource = new UserResource(userService);


    @BeforeEach
    void setUp() {
        final List<UserModel> users = getUsersModel();
        Mockito.when(userService.getAll()).thenReturn(users);
        Mockito.when(userService.getById(NumberUtils.LONG_ONE)).thenReturn(users
                .get(NumberUtils.INTEGER_ZERO));
        Mockito.when(userService.create(users
                .get(NumberUtils.INTEGER_ZERO))).thenReturn(getUserResponse(users
                .get(NumberUtils.INTEGER_ZERO)));
        Mockito.when(userService.update(users
                .get(NumberUtils.INTEGER_ZERO), NumberUtils.LONG_ONE)).thenReturn(getUserResponse(users
                .get(NumberUtils.INTEGER_ZERO)));
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void getAll() {
        final ResponseEntity responseEntity = userResource.getAll();
        Assertions.assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value());
    }

    @Test
    void getAllById() {
        final ResponseEntity responseEntity = userResource.getAllById(NumberUtils.LONG_ONE);
        Assertions.assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value());
    }

    @Test
    void create() {
        final ResponseEntity responseEntity = userResource.create(getUsersModel()
                .get(NumberUtils.INTEGER_ZERO));
        Assertions.assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.CREATED.value());
    }

    @Test
    void update() {
        final ResponseEntity responseEntity = userResource.update(NumberUtils.LONG_ZERO, getUsersModel()
                .get(NumberUtils.INTEGER_ZERO));
        Assertions.assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void delete() {
        final ResponseEntity responseEntity = userResource.delete(NumberUtils.LONG_ZERO);
        Assertions.assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.BAD_REQUEST.value());
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

    private UserResponse getUserResponse(final UserModel userModel) {
        final UserResponse userResponse = new UserResponse();
        userResponse.setUserId(userModel.getUserId());
        userResponse.setActive(userModel.isActive());
        userResponse.setCreated(userModel.getCreated());
        userResponse.setModified(userModel.getModified());
        userResponse.setToken(userModel.getToken());
        return userResponse;
    }
}