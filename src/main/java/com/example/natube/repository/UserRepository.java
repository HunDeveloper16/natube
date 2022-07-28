package com.example.natube.repository;

import com.example.natube.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User ,Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByNickname(String nickname);


    User findAllById(Long id);

}
