package com.bubyrevdmitriyGmail.demo.repository;

import com.bubyrevdmitriyGmail.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {

    User findUserByUsername(String username);

    User findUserById(Long id);

}
