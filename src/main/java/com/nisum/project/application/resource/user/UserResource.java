package com.nisum.project.application.resource.user;


import com.nisum.project.application.port.UserService;
import com.nisum.project.domain.model.UserModel;
import com.nisum.project.infrastructure.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserResource implements Serializable {

    private final UserService userService;

    @Value("${message.error.validate}")
    private String messageError;
    @Value("${message.email.exist}")
    private String messageEmail;
    @Value("${message.id.not.found}")
    private String messageNotFound;
    @Value("${message.delete.complete}")
    private String messageDelete;

    @Autowired
    public UserResource(final UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public ResponseEntity getAll() {
        log.info("init process get All users");
        final List<UserModel> users = userService.getAll();
        if (CollectionUtils.isEmpty(users)) {
            return ResponseUtil.result(this.messageError,
                    HttpStatus.BAD_REQUEST, this.messageNotFound);
        }
        return ResponseUtil.result(users,
                HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity getAllById(@PathVariable(value = "id") final Long id) {
        log.info("init process getAllById users");
        final UserModel userModel = userService.getById(id);
        if (userModel == null) {
            return ResponseUtil.result(this.messageError,
                    HttpStatus.BAD_REQUEST, this.messageNotFound);
        }
        return ResponseUtil.result(userService.getById(id),
                HttpStatus.OK);

    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@Valid @RequestBody final UserModel userModel) {
        log.info("init process create user");
        if (userService.isExistByEmail(userModel.getEmail())) {
            return ResponseUtil.result(this.messageError,
                    HttpStatus.CONFLICT, this.messageEmail);
        }
        return ResponseUtil.result(userService.create(userModel), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable(value = "id") final Long id,
                                 @Valid @RequestBody final UserModel userModel) {
        log.info("init process update user");
        final UserModel user = userService.getById(id);
        if (user == null) {
            return ResponseUtil.result(this.messageError,
                    HttpStatus.BAD_REQUEST, this.messageNotFound);
        }
        if (userService.isExistByIdAndEmail(id, userModel.getEmail())) {
            return ResponseUtil.result(this.messageError,
                    HttpStatus.CONFLICT, this.messageEmail);
        }
        userModel.setToken(user.getToken());
        userModel.setCreated(user.getCreated());
        userModel.setModified(user.getModified());
        userModel.setLastLogin(user.getLastLogin());
        return ResponseUtil.result(userService.update(userModel, id), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") final Long id) {
        log.info("init process update user");
        if (!userService.isExistById(id)) {
            return ResponseUtil.result(this.messageError,
                    HttpStatus.BAD_REQUEST, this.messageNotFound);
        }
        userService.delete(id);
        return ResponseUtil.result(this.messageDelete, HttpStatus.OK, null);

    }


}
