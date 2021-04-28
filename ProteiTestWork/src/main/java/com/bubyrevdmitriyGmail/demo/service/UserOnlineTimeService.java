package com.bubyrevdmitriyGmail.demo.service;

import com.bubyrevdmitriyGmail.demo.model.User;
import com.bubyrevdmitriyGmail.demo.model.UserOnlineTime;
import com.bubyrevdmitriyGmail.demo.repository.UserOnlineTimeRepo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserOnlineTimeService {

    private final UserOnlineTimeRepo userOnlineTimeRepo;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserOnlineTimeService(UserOnlineTimeRepo userOnlineTimeRepoRepo) {
        this.userOnlineTimeRepo = userOnlineTimeRepoRepo;
    }

    @Value("${user.status.time.minutes.wait}")
    private short userOnlinePeriod;


    public  UserOnlineTime createOrUpdate(User user) {
        UserOnlineTime userOnlineTimeFromDb = userOnlineTimeRepo.findUserOnlineTimeByUser(user);
        if (userOnlineTimeFromDb != null) {
            //update
            System.out.println("UPDATING");
            userOnlineTimeFromDb.setTimeLeftMilliseconds((long) (userOnlinePeriod*60*1000));

            userOnlineTimeRepo.save(userOnlineTimeFromDb);

            logger.info("IN updated - userOnlineTime: {} successfully updated", userOnlineTimeFromDb);

            return userOnlineTimeFromDb;
            //return false;
        } else {
            //created
            System.out.println("CREATING");
            UserOnlineTime userOnlineTime = new UserOnlineTime();

            userOnlineTime.setUser(user);
            userOnlineTime.setTimeLeftMilliseconds((long) (userOnlinePeriod*60*1000));

            userOnlineTimeRepo.save(userOnlineTime);

            logger.info("IN created - userOnlineTime: {} successfully registered", userOnlineTime);

            return userOnlineTime;
        }
    }

    public UserOnlineTime update(Long userOnlineTimeId, Long newMilliseconds) {
        UserOnlineTime result = userOnlineTimeRepo.findUserOnlineTimeById(userOnlineTimeId);
        if (result != null) {
            result.setTimeLeftMilliseconds(newMilliseconds);
            userOnlineTimeRepo.save(result);
        }
        return result;
    }

    public UserOnlineTime findById(Long Id) {
        UserOnlineTime result = userOnlineTimeRepo.findUserOnlineTimeById(Id);

        if (result == null) {
            logger.warn("IN findById - no userOnlineTime data found by id: {}", Id);
            return null;
        }

        logger.info("IN findById - userOnlineTime data: {} found by id: {}", result, Id);
        return result;
    }

    public void delete(Long Id) {
        userOnlineTimeRepo.deleteById(Id);
        logger.info("IN delete - userOnlineTime data with id: {} successfully deleted", Id);
    }
}
