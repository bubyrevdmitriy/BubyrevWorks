package com.bubyrevdmitriygmail.LibraryBubyrev.controller;

import com.bubyrevdmitriygmail.LibraryBubyrev.domain.Author;
import com.bubyrevdmitriygmail.LibraryBubyrev.domain.Book;
import com.bubyrevdmitriygmail.LibraryBubyrev.domain.BookAmount;
import com.bubyrevdmitriygmail.LibraryBubyrev.domain.User;
import com.bubyrevdmitriygmail.LibraryBubyrev.repos.*;
import com.bubyrevdmitriygmail.LibraryBubyrev.service.BookService;
import com.bubyrevdmitriygmail.LibraryBubyrev.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class BooksController {
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private BookAmountRepo bookAmountRepo;
    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    OrderService orderService;
    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public String books(@RequestParam(required = false, defaultValue =  "") String filterName,
                        Model model,
                        @PageableDefault( sort={ "id" }, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Book> page= bookRepo.findAll(pageable);

        if (filterName != null && !filterName.isEmpty()){
            page =bookRepo.findByName(filterName, pageable);
        }

        model.addAttribute("filterName", filterName);
        model.addAttribute("page", page);
        model.addAttribute("url", "/books");
        return "books";
    }

    @PreAuthorize("hasAuthority('LIBRARIAN')")
    @PostMapping("/books")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam("file") MultipartFile file,
                      @RequestParam("authorId") String authorId,
                      @RequestParam(name="bookAmount", required=false) Integer bookAmount,
                      @Valid Book book,
                      BindingResult bindingResult,
                      Model model,
                      @PageableDefault( sort={ "id" }, direction = Sort.Direction.DESC) Pageable pageable
    ) throws IOException {
        model.addAttribute("amountBooks", bookAmount);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("book", book);
        }

        if ( StringUtils.isEmpty(authorId)) {
            model.addAttribute("authorError", "Заполните Id автора!");
        } else {
            Author authorFromDb = authorRepo.findAuthorById(Long.parseLong(authorId));

            if (authorFromDb == null || StringUtils.isEmpty(authorFromDb)) {

                model.addAttribute("authorError", "Такого автора не существует!");
            } else {
                bookService.addNewBook(book, file, authorFromDb, bookAmount);
                model.addAttribute("book", null);
                model.addAttribute("amountBooks", null);
            }
        }
        Page<Book> page= bookRepo.findAll(pageable);

        model.addAttribute("page", page);
        model.addAttribute("url", "/books");
        return "books";
    }

    @GetMapping("/books/{book}")
    public String getCurBook(
            @PathVariable String book,
            Model model
    ) {
        Book bookFromDb = bookRepo.findBookById(Long.valueOf(book));
        model.addAttribute("book", bookFromDb);
        return "bookCur";
    }

    @PreAuthorize("hasAuthority('LIBRARIAN')")
    @PostMapping("/books/{bookId}")
    public String updateCurBook(
            @PathVariable String bookId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("authorId") String authorId,
            @RequestParam("info") String info,
            @RequestParam(name="bookAmount", required=false) Integer bookAmount,
            @RequestParam(name="bookAmountReserved", required=false) Integer bookAmountReserved,
            @RequestParam(name="bookAmountAtUsers", required=false) Integer bookAmountAtUsers,
            @Valid Book book,
            BindingResult bindingResult,
            Model model
    ) throws IOException {

        Book bookFromDb = bookRepo.findBookById(Long.valueOf(bookId));

        if ( StringUtils.isEmpty(authorId)) {
            model.addAttribute("authorError", "Заполните Id автора!");
            model.addAttribute("book", bookFromDb);
            return "bookCur";
        }
        Author authorFromDb = authorRepo.findAuthorById(Long.parseLong(authorId));

        if (authorFromDb == null  || StringUtils.isEmpty(authorFromDb)) {
            model.addAttribute("authorError", "Такого автора не существует!");
            model.addAttribute("book", bookFromDb);
            return "bookCur";
        }
            if (bindingResult.hasErrors()) {
                Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
                model.mergeAttributes(errorsMap);
                model.addAttribute("book", book);
            } else {
                bookService.updateBook(bookFromDb, book, file, authorFromDb, info);
            }
            bookService.updateBookAmount(bookFromDb, bookAmount, bookAmountReserved, bookAmountAtUsers);

            model.addAttribute("book", bookFromDb);
            return "redirect:{bookId}";
    }

    @PreAuthorize("hasAuthority('LIBRARIAN')")
    @GetMapping("/books/delete/{bookId}")
    public String deleteCurBook(
            @PathVariable String bookId,
            Model model
    ) {
        Book bookFromDb = bookRepo.findBookById(Long.valueOf(bookId));
        bookRepo.delete(bookFromDb);
        return "redirect:/books";
    }


    @GetMapping("/books/makeOrder/{bookId}")
    public String makeOrderCurBook(
            @AuthenticationPrincipal User user,
            @PathVariable String bookId,
            Model model
    ) {
        Book bookFromDb = bookRepo.findBookById(Long.valueOf(bookId));
        BookAmount bookAmountFromDb = bookAmountRepo.findBookAmountByBook(bookFromDb);

        if (bookAmountFromDb == null || StringUtils.isEmpty(bookAmountFromDb)) {
            return "redirect:/books/{bookId}";
        } else {
            if (bookAmountFromDb.getBookAmountAtLibrary() >= 1) {
                orderService.addNewBookToOrder(user, bookFromDb, bookAmountFromDb);
            } else {
                return "redirect:/books/{bookId}";
            }
            return "redirect:/books";
        }
    }
}
