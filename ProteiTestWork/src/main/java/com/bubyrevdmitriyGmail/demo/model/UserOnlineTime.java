package com.bubyrevdmitriyGmail.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_online_time")
public class UserOnlineTime {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="time_left")
    private Long timeLeftMilliseconds;

    @OneToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTimeLeftMilliseconds() {
        return timeLeftMilliseconds;
    }

    public void setTimeLeftMilliseconds(Long timeLeftMilliseconds) {
        this.timeLeftMilliseconds = timeLeftMilliseconds;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserOnlineTime() {
    }


    @Override
    public String toString() {
        return "UserOnlineTime{" +
                "id=" + id +
                ", user=" + user +
                '}';
    }
}
