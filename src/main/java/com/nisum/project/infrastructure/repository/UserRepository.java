package com.nisum.project.infrastructure.repository;

import com.nisum.project.infrastructure.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    @Query("select u from User u where UPPER(u.email) = UPPER(:email)")
    Optional<User> userByEmail(@Param("email") final String email);

    @Query("select u from User u where u.userId = :userId")
    Optional<User> getAllById(@Param("userId") final Long userId);

    @Query("select u from User u where u.userId <>:userId AND UPPER(u.email) = UPPER(:email)")
    Optional<User> isExistByIdAndEmail(@Param("userId") final Long userId, @Param("email") final String email);
}
