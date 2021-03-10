package com.bubyrevdmitriygmail.LibraryBubyrev.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;


@Entity
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;


    @NotBlank(message = "Пожалуйста, заполните название книги")
    @Length(max = 255, message = "Имя книги слишком длинное(более 255 символов)")
    private String name;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="author_id")
    private Author author;

    private String year;
    private String country;
    private String tag;

    private String filename;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Order> orders;

    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BookAmount bookAmount;

    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAmountBooks() {
        //return  author !=null ? author.getLastName(): "<none>";
        int amount = bookAmount != null ? bookAmount.getBookAmountAtLibrary(): 0;
        return String.valueOf(amount);
    }

    public String getAmountBooksReserved() {
        //return  author !=null ? author.getLastName(): "<none>";
        int amount = bookAmount != null ? bookAmount.getBookAmountReserved(): 0;
        return String.valueOf(amount);
    }

    public String getAmountBooksAtUsers() {
        //return  author !=null ? author.getLastName(): "<none>";
        int amount = bookAmount != null ? bookAmount.getBookAmountAtUsers(): 0;
        return String.valueOf(amount);
    }


    public int getAmountBooksInt() {
        //return  author !=null ? author.getLastName(): "<none>";
        int amountBooks = bookAmount != null ? bookAmount.getBookAmountAtLibrary(): 0;
        return amountBooks;
    }





    public String getAuthorName() {
        //return  author !=null ? author.getLastName(): "<none>";
        String s1 = author !=null ? author.getFirstName(): "<none>";
        String s2 = author !=null ? author.getLastName(): "<none>";
        String s3 = author !=null ? author.getMiddleName(): "<none>";;
        return  s1+" "+s3+" "+s2;
    }

    public Long getAuthorId() {
        return  author !=null ? author.getId(): 0;
    }

    public Book(String name, Author author, String year, String country, String tag) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.country = country;
        this.tag = tag;
    }

    public Book() {
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }



    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

   public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }


}
