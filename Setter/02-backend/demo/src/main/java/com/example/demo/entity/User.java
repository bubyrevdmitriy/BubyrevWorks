package com.example.demo.entity;

import com.example.demo.entity.BaseEntity.BaseEntity;
import com.example.demo.entity.enums.ERole;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "usr")
@Data
public class User extends BaseEntity implements UserDetails {

    @Column(name="password", length = 3000)
    private String password;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(name="middle_name")
    private String middleName;

    @Column(name="phone", unique = true)
    private String phone;

    @Column(name="email", unique = true)
    private String email;

    @Column(name="activation_code_phone")
    private String activationCodePhone;

    @Column(name="activation_code_email")
    private String activationCodeEmail;

    @Column(name="password_restore_code")
    private String passwordRestoreCode;

    @Column(name="born_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date bornDate;

    @Column(columnDefinition = "text", name="bio")
    private String bio;

    @Column(name="enabled")
    private Boolean enabled;

    @ElementCollection(targetClass = ERole.class)
    @CollectionTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"))
    private Set<ERole> roles = new HashSet<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private SmallAvatar smallAvatar;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "user", orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    private Long profileImageId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "user", orphanRemoval = true)
    private List<CommonImage> commonImages = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Channel> channel;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "user", orphanRemoval = true)
    private List<AudioFile> audioFiles = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "user", orphanRemoval = true)
    private List<VideoFile> videoFiles = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "senderUser", orphanRemoval = true)
    private List<UserFriendInvite> friendInvitesSend = new ArrayList<>();

    private Integer friendInvitesSendAmount;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "recipientUser", orphanRemoval = true)
    private List<UserFriendInvite> friendInvitesReceived = new ArrayList<>();

    private Integer friendInvitesReceivedAmount;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> userFriends;

    private Integer userFriendsAmount;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "senderUser", orphanRemoval = true)
    private List<UserFriendPair> friendPairsSend = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "recipientUser", orphanRemoval = true)
    private List<UserFriendPair> friendPairsReceived = new ArrayList<>();

    private Integer groupInvitesSendAmount;
    private Integer groupInvitesReceivedAmount;
    private Integer userGroupsAmount;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "senderUser", orphanRemoval = true)
    private List<UserGroupInvite> groupInvitesSend = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "recipientUser", orphanRemoval = true)
    private List<UserGroupInvite> groupInvitesReceived = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "user", orphanRemoval = true)
    private List<UserGroupPair> groupPairs = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "user", orphanRemoval = true)
    private List<UserLike> userLikes = new ArrayList<>();

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    public User() {
    }

    public User(String password,
                String firstName,
                String lastName,
                String email,
                String activationCodeEmail,
                Date bornDate,
                Collection<? extends GrantedAuthority> authorities
    ) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.activationCodeEmail = activationCodeEmail;
        this.bornDate = bornDate;
        this.authorities = authorities;
        this.enabled = true;
    }

    /**
     *
     * SECURITY
     */

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
