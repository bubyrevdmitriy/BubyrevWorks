package com.bubyrevdmitriyGmail.demo.dto;

import com.bubyrevdmitriyGmail.demo.model.UserStatus;
import lombok.Data;

@Data
public class ChangeUserStatusDto extends FindUserByIdDto{

    private UserStatus newStatus;

    public UserStatus getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(UserStatus newStatus) {
        this.newStatus = newStatus;
    }

    public ChangeUserStatusDto() {
    }
}
