package com.server.todoapp.repository;

import com.server.todoapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Long> {
    User findByUsername(String username);

//    User findByUserName(String username);
}
