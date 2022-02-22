package com.nisum.project.domain.model;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Data
@ToString
public class UserModel {

    private Long userId;
    @NotBlank
    private String name;
    @NotBlank
    @Email(message = "{message.error.validate.email}")
    private String email;
    @NotBlank
    private String password;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private UUID token;
    private boolean isActive;
    private List<PhoneModel> phones;
}
