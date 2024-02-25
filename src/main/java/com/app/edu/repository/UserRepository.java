package com.app.edu.repository;

import com.app.edu.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    public UserEntity findByEmail(String email);

    public UserEntity findByUsername(String username);

    public boolean existsByEmail(String email);

    public boolean existsByUsername(String username);

}
