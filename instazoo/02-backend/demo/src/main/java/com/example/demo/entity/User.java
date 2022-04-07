package com.example.demo.entity;

import com.example.demo.entity.baseEntity.BaseEntity;
import com.example.demo.entity.enums.ERole;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "usr")
@Data
public class User extends BaseEntity implements UserDetails {

    @Column(nullable = false)//по этим полям происходит валидация перед сохранением сущности
    private String name;
    @Column(unique = true, updatable = false)
    private String username;
    @Column(nullable = false)
    private String lastname;
    @Column(unique = true)
    private String email;
    @Column(columnDefinition = "text")
    private String bio;
    @Column(length = 3000)
    private String password;

    @OneToOne
    private ImageModel profileImage;

    @ElementCollection(targetClass = ERole.class)//используем данную анотацию для привязки простых элементов, которым является список
    @CollectionTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"))
    private Set<ERole> roles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "user", orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "user", orphanRemoval = true)
    private List<ImageModel> imageModels = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "user", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "user", orphanRemoval = true)
    private List<UserLike> userLikes = new ArrayList<>();

    @Transient//данная анотация применяется при сериальзации и десериализации методов
    private Collection<? extends GrantedAuthority> authorities;

    public User() {
    }

    public User(Long id,
                String username,
                String email,
                String password,
                Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }


    // methods from UserDetails
    @Override
    public String getPassword() {
        return password;
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
}
