package com.bubyrevdmitriygmail.LibraryBubyrev.repos;

import com.bubyrevdmitriygmail.LibraryBubyrev.domain.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepo extends CrudRepository <Author, Long> {

    Page<Author> findByTag(String Tag, Pageable pageable);

    Page<Author> findByLastName(String lastName, Pageable pageable);

    Page<Author> findByTagAndLastName(String Tag, String lastName, Pageable pageable);

    Page<Author> findAll(Pageable pageable);

    Author findAuthorById(Long id);

}
