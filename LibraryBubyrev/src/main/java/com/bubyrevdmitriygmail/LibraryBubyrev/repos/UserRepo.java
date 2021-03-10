package com.bubyrevdmitriygmail.LibraryBubyrev.repos;

import com.bubyrevdmitriygmail.LibraryBubyrev.domain.Book;
import com.bubyrevdmitriygmail.LibraryBubyrev.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);

    User findUserById(Long id);

    User findByActivationCodeEmail(String code);

    Page<User> findByUsername(String username, Pageable pageable);
    Page<User> findAll(Pageable pageable);
}
