package com.sparta.hanghaememo.repository;


import com.sparta.hanghaememo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
//    List<User> findAllByOrderByModifiedAtDesc();
    Optional<User> findByUsername(String username);
}