package com.bubyrevdmitriyGmail.demo.repository;

import com.bubyrevdmitriyGmail.demo.model.User;
import com.bubyrevdmitriyGmail.demo.model.UserOnlineTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserOnlineTimeRepo extends CrudRepository<UserOnlineTime, Long> {

    UserOnlineTime findUserOnlineTimeById(Long id);

    UserOnlineTime findUserOnlineTimeByUser(User user);

    @Override
    void delete(UserOnlineTime userOnlineTime);

    @Override
    void deleteById(Long id);
}
