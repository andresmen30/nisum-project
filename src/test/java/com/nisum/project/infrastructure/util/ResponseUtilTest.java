package com.nisum.project.infrastructure.util;

import com.nisum.project.domain.model.PhoneModel;
import com.nisum.project.domain.model.UserModel;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class ResponseUtilTest {

    @Test
    void result() {
        final String[] details = {"El correo ya registrado"};
        Assertions.assertEquals(ResponseUtil.result("Validation error",
                HttpStatus.BAD_REQUEST, details).getStatusCodeValue(), 400);
    }

    @Test
    void testResult() {
        Assertions.assertEquals(ResponseUtil.result(getUserModel(),
                HttpStatus.OK).getStatusCodeValue(), 200);
    }


    private UserModel getUserModel() {
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
        return userModel;
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