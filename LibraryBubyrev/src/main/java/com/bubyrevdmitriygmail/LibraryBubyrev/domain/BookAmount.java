package com.bubyrevdmitriygmail.LibraryBubyrev.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "bookAmount")
public class BookAmount {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;


    private int bookAmountAtLibrary;
    private int bookAmountReserved;
    private int bookAmountAtUsers;

    @OneToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="book_id")
    private Book book;


    public BookAmount(int bookAmountAtLibrary, Book book) {
        this.bookAmountAtLibrary = bookAmountAtLibrary;
        this.bookAmountReserved = 0;
        this.bookAmountAtUsers = 0;
        this.book = book;
    }

    public BookAmount(Book book) {
        this.bookAmountAtLibrary = 0;
        this.bookAmountReserved = 0;
        this.bookAmountAtUsers = 0;
        this.book = book;
    }

    public BookAmount() {
    }

    public void setAll(int bookAmountAtLibrary, int bookAmountReserved, int bookAmountAtUsers) {
        this.bookAmountAtLibrary = bookAmountAtLibrary;
        this.bookAmountReserved = bookAmountReserved;
        this.bookAmountAtUsers = bookAmountAtUsers;
    }


    public Long getId() {
        return id;
    }

    public int getBookAmountAtLibrary() {
        return bookAmountAtLibrary;
    }

    public void setBookAmountAtLibrary(int bookAmountAtLibrary) {
        this.bookAmountAtLibrary = bookAmountAtLibrary;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getBookAmountReserved() {
        return bookAmountReserved;
    }

    public void setBookAmountReserved(int bookAmountReserved) {
        this.bookAmountReserved = bookAmountReserved;
    }

    public int getBookAmountAtUsers() {
        return bookAmountAtUsers;
    }

    public void setBookAmountAtUsers(int bookAmountAtUsers) {
        this.bookAmountAtUsers = bookAmountAtUsers;
    }
}
