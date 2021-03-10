package com.bubyrevdmitriygmail.LibraryBubyrev.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "ordr")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="book_id")
    private Book book;

    @ElementCollection(targetClass = OrderRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "order_role", joinColumns = @JoinColumn(name = "order_id"))
    @Enumerated(EnumType.STRING)
    private Set<OrderRole> Orderroles;

    private String startTime;
    private Long stockTime;

    public String getCurrentLocalDateTimeStamp() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public Order(User user, Book book) {
        this.user = user;
        this.book = book;
        this.startTime = getCurrentLocalDateTimeStamp();
    }

    public Order() {
    }

    public String getUserName() {
        return  user !=null ? user.getUsername(): "<none>";
    }
    public String getBookName() {
        return  book !=null ? book.getName(): "<none>";
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStockTime() {
        //return stockTime;
        return  stockTime !=null ? String.valueOf(stockTime) : "<none>";
    }



    public void setStockTime(Long stockTime) {
        this.stockTime = stockTime;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public Set<OrderRole> getRoles() {
        return Orderroles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Set<OrderRole> getOrderroles() {
        return Orderroles;
    }

    public void setOrderroles(Set<OrderRole> orderroles) {
        Orderroles = orderroles;
    }

    public Long getStockTimeLong() {
        return  this.stockTime;
    }


}
