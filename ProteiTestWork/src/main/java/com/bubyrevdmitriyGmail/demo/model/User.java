package com.bubyrevdmitriyGmail.demo.model;

import com.bubyrevdmitriyGmail.demo.dto.RegistrationRequestDto;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "usr")
@Data
public class User extends BaseEntity implements UserDetails {

    @Column(name = "username")
    @NotBlank(message="Имя пользователя не может")
    private String username;

    @Column(name = "password")
    @NotBlank(message="Пароль не может быть пустым")
    private String password;

    @Column(name = "email")
    @Email(message="Электронная почта введена некорректно")
    @NotBlank(message="Заполните адрес электронной почты!")
    private String email;

    @Column(name = "phone_number")
    @NotBlank(message="Заполните номер телефона!")
    @Pattern(regexp = "\\+(?:[0-9] ?){6,14}[0-9]$")
    private String phoneNumber;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    @Enumerated(EnumType.STRING)
    private List<UserRole> userRoles;

    @Enumerated(EnumType.STRING)
    @Column(name="user_status")
    private UserStatus userStatus;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserOnlineTime userOnlineTime;

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getUserRoles();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public User() {
    }

    public User(
            @NotBlank(message = "Имя пользователя не может") String username,
            @NotBlank(message = "Пароль не может быть пустым") String password,
            @Email(message = "Электронная почта введена некорректно") @NotBlank(message = "Заполните адрес электронной почты!") String email,
            @NotBlank(message = "Заполните номер телефона!") @Pattern(regexp = "\\+(?:[0-9] ?){6,14}[0-9]$") String phoneNumber
    ) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public User(RegistrationRequestDto requestDto) {
            this.username = requestDto.getUsername();
            this.password = requestDto.getPassword();
            this.email = requestDto.getEmail();
            this.phoneNumber = requestDto.getPhoneNumber();
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + ' ' +
                ", password='" + password + ' ' +
                ", email='" + email + ' ' +
               ", phoneNumber='" + phoneNumber + ' ' +
                ", userRoles=" + userRoles +
                ", userStatus=" + userStatus +
                '}';
    }
}
