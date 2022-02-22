package com.nisum.project.infrastructure.resource.user.response;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@ToString
@JsonPropertyOrder({"userId", "created", "modified", "lastLogin", "toke", "isActive"})
public class UserResponse implements Serializable {

    private Long userId;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private UUID token;
    private boolean isActive;

}
