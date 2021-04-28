package com.bubyrevdmitriyGmail.demo.service;

import com.bubyrevdmitriyGmail.demo.model.User;
import com.bubyrevdmitriyGmail.demo.model.UserOnlineTime;
import com.bubyrevdmitriyGmail.demo.model.UserStatus;
import com.bubyrevdmitriyGmail.demo.repository.UserOnlineTimeRepo;
import com.bubyrevdmitriyGmail.demo.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@EnableAsync
public class UserStatusTimer {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserOnlineTimeRepo userOnlineTimeRepo;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    private Long userId;

    private Long userOnlineTimeId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserOnlineTimeId() {
        return userOnlineTimeId;
    }

    public void setUserOnlineTimeId(Long userOnlineTimeId) {
        this.userOnlineTimeId = userOnlineTimeId;
    }

    public UserStatusTimer() {
    }


    @Async
    public void timerStart() {

        UserOnlineTime userOnlineTime = userOnlineTimeRepo.findUserOnlineTimeById(userOnlineTimeId);
        long milliseconds = userOnlineTime.getTimeLeftMilliseconds();
        int timePeriod = 5*1000;//5 секунд
        logger.info("Start timer - user with id: {} userOnlineTimeId with id {}", userId, userOnlineTimeId);

        while (milliseconds>0){
            try {

                milliseconds = milliseconds-timePeriod;
                Thread.sleep(timePeriod);

                userOnlineTime = userOnlineTimeRepo.findUserOnlineTimeById(userOnlineTimeId);
                if (userOnlineTime.getTimeLeftMilliseconds()-timePeriod>milliseconds) {
                    break;
                } else {
                    userOnlineTime.setTimeLeftMilliseconds(milliseconds);
                    userOnlineTimeRepo.save(userOnlineTime);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        userOnlineTime = userOnlineTimeRepo.findUserOnlineTimeById(userOnlineTimeId);
        if (userOnlineTime.getTimeLeftMilliseconds() == 0) {
            User user = userRepo.findUserById(userId);
            user.setUserStatus(UserStatus.valueOf("AWAY"));
            userRepo.save(user);
            logger.info("Finish timer - user with id: {} userOnlineTimeId with id {} new status AWAY", userId, userOnlineTimeId);
        }
        //в рамках класса UserStatusTimer мы не удаляем запись о сессии пользователя из таблицы user_online_time
        //предполагается, что удаление строчки из таблицы user_online_time будет осуществляться при выходе пользователя из системы
    }
}
