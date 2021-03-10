package com.bubyrevdmitriygmail.LibraryBubyrev.repos;

import com.bubyrevdmitriygmail.LibraryBubyrev.domain.Book;
import com.bubyrevdmitriygmail.LibraryBubyrev.domain.BookAmount;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookAmountRepo extends CrudRepository<BookAmount, Long> {
    BookAmount findBookAmountByBook(Book book);
}


