package com.bubyrevdmitriygmail.LibraryBubyrev.service;

import com.bubyrevdmitriygmail.LibraryBubyrev.domain.Author;
import com.bubyrevdmitriygmail.LibraryBubyrev.domain.Book;
import com.bubyrevdmitriygmail.LibraryBubyrev.domain.BookAmount;
import com.bubyrevdmitriygmail.LibraryBubyrev.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class BookService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private BookAmountRepo bookAmountRepo;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${hostname}")
    private String hostname;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    OrderService orderService;


    public void addNewBook(Book book, MultipartFile file, Author authorFromDb, Integer bookAmount) throws IOException {
        book.setAuthor(authorFromDb);
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            book.setFilename(resultFilename);
        }
        bookRepo.save(book);
        if ( !StringUtils.isEmpty(bookAmount)) {
            BookAmount bookAmountNew;
            if (bookAmount==0) {
                bookAmountNew = new BookAmount(0, book);
                bookAmountRepo.save(bookAmountNew);
            } else {
                bookAmountNew = new BookAmount(bookAmount, book);
                bookAmountRepo.save(bookAmountNew);
            }
        }
    }

    public void updateBook(Book  bookFromDb, Book book, MultipartFile file, Author authorFromDb, String info) throws IOException {
        bookFromDb.setInfo(info);
        bookFromDb.setAuthor(authorFromDb);
        bookFromDb.setName(book.getName());
        bookFromDb.setCountry(book.getCountry());
        bookFromDb.setTag(book.getTag());
        bookFromDb.setYear(book.getYear());
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            bookFromDb.setFilename(resultFilename);
        }
        bookRepo.save(bookFromDb);
    }

    public void updateBookAmount(Book bookFromDb, Integer bookAmount, Integer bookAmountReserved, Integer bookAmountAtUsers) {
        BookAmount bookAmountFromDb = bookAmountRepo.findBookAmountByBook(bookFromDb);
        if (bookAmountFromDb == null || StringUtils.isEmpty(bookAmountFromDb)) {
            BookAmount bookAmountNew = new BookAmount(bookFromDb);

            if ( !StringUtils.isEmpty(bookAmount)) {
                bookAmountNew.setBookAmountAtLibrary(bookAmount);
            }
            if ( !StringUtils.isEmpty(bookAmountReserved)) {
                bookAmountNew.setBookAmountReserved(bookAmountReserved);
            }
            if ( !StringUtils.isEmpty(bookAmountAtUsers)) {
                bookAmountNew.setBookAmountAtUsers(bookAmountAtUsers);
            }
            bookAmountRepo.save(bookAmountNew);

        } else {
            bookAmountFromDb.setBookAmountAtLibrary(bookAmount);
            bookAmountFromDb.setBookAmountReserved(bookAmountReserved);
            bookAmountFromDb.setBookAmountAtUsers(bookAmountAtUsers);
            bookAmountRepo.save(bookAmountFromDb);
        }
    }




}
