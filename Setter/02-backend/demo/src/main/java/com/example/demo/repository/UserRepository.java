package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByActivationCodeEmail(String activationCodeEmail);
    Optional<User> findUserByPasswordRestoreCode(String passwordRestoreCode);

    long count();
    List<User> findAllByOrderByCreatedDateDesc(Pageable pageable);

    long countByLastNameContaining(String lastName);
    List<User> findByLastNameContainingOrderByCreatedDateDesc(String lastName, Pageable pageable);
}
