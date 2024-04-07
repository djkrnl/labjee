package com.example.labjee.repositories;

import com.example.labjee.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);

    List<User> findAllBy();
}
