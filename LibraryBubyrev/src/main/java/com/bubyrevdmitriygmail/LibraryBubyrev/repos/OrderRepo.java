package com.bubyrevdmitriygmail.LibraryBubyrev.repos;

import com.bubyrevdmitriygmail.LibraryBubyrev.domain.Book;
import com.bubyrevdmitriygmail.LibraryBubyrev.domain.Order;
import com.bubyrevdmitriygmail.LibraryBubyrev.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderRepo extends CrudRepository<Order, Long> {
    Page<Order> findByUser(User user, Pageable pageable);

    //Author findByUser(User user);

    Order findOrderById(Long Id);

    Page<Order> findOrderById(Long Id, Pageable pageable);

    Page<Order> findAll(Pageable pageable);
    //Order findOrderByAuthor(Author author);
}

