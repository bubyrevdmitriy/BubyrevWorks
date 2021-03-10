package com.bubyrevdmitriygmail.LibraryBubyrev.repos;

import com.bubyrevdmitriygmail.LibraryBubyrev.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface BookRepo extends CrudRepository<Book, Long> {

    Page<Book> findByTag(String Tag, Pageable pageable);
    Page<Book> findAll(Pageable pageable);
    Book findBookById(Long id);
    Page<Book> findByName(String Name, Pageable pageable);
}


