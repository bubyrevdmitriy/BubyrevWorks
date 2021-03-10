package com.bubyrevdmitriygmail.LibraryBubyrev.controller;

import com.bubyrevdmitriygmail.LibraryBubyrev.domain.Author;
import com.bubyrevdmitriygmail.LibraryBubyrev.domain.Book;
import com.bubyrevdmitriygmail.LibraryBubyrev.domain.User;
import com.bubyrevdmitriygmail.LibraryBubyrev.repos.AuthorRepo;
import com.bubyrevdmitriygmail.LibraryBubyrev.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Controller
public class AuthorController {
    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private AuthorService authorSevice;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/authors")
    public String main(@RequestParam(required = false, defaultValue =  "") String filterTag,
                       @RequestParam(required = false, defaultValue =  "") String filterAuthorLastName,
                       @PageableDefault( sort={ "id" }, direction = Sort.Direction.DESC) Pageable pageable,
                       Model model
    ) {
        Page<Author> page = authorRepo.findAll(pageable);

        if((filterTag != null && !filterTag.isEmpty()) && (filterAuthorLastName != null && !filterAuthorLastName.isEmpty())) {
            page =authorRepo.findByTagAndLastName(filterTag, filterAuthorLastName, pageable);
        } else {
            if (filterAuthorLastName != null && !filterAuthorLastName.isEmpty()){
                page =authorRepo.findByLastName(filterAuthorLastName, pageable);
            } else {
                if (filterTag != null && !filterTag.isEmpty()){
                    page =authorRepo.findByTag(filterTag, pageable);
                }
            }
        }
        model.addAttribute("page", page);
        model.addAttribute("url", "/authors");
        model.addAttribute("filterTag", filterTag);
        return "authors";
    }
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    @PostMapping("/authors")
    public String add(@AuthenticationPrincipal User user,
                      @PageableDefault( sort={ "id" }, direction = Sort.Direction.DESC) Pageable pageable,
                      @RequestParam("file") MultipartFile file,
                      @Valid Author author,
                      BindingResult bindingResult,
                      Model model
    ) throws IOException {

            if(bindingResult.hasErrors()) {
                Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
                model.mergeAttributes(errorsMap);
                model.addAttribute("author", author);
            } else {
                authorSevice.addNewAuthor(author, file);
                model.addAttribute("author", null);
            }

        Page<Author> page = authorRepo.findAll(pageable);

        model.addAttribute("page", page);
        model.addAttribute("url", "/authors");
        return "authors";
    }

    @GetMapping("/authors/{author}")
    public String getCurBook(
            @PathVariable String author,
            Model model
    ) {
        Author authorFromDb = authorRepo.findAuthorById(Long.valueOf(author));
        Set<Book> books = authorFromDb.getBooks();
        model.addAttribute("books", books);
        model.addAttribute("author", authorFromDb);
        return "authorCur";
    }

    @PostMapping("/authors/{author}")
    public String updateCurBook(
            @RequestParam("file") MultipartFile file,
            @Valid Author author,
            BindingResult bindingResult,
            Model model
    ) throws IOException {
        if(bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("author", author);
        } else {
            authorSevice.updateAuthor(author, file);
        }
            Author authorFromDb = authorRepo.findAuthorById(author.getId());
            model.addAttribute("author", authorFromDb);
            return "redirect:/authors/{author}";
    }

    @PreAuthorize("hasAuthority('LIBRARIAN')")
    @GetMapping("authors/delete/{author}")
    public String deleteCurBook(
            @PathVariable String author,
            Model model
    ) {
        Author authorFromDb = authorRepo.findAuthorById(Long.valueOf(author));
        authorRepo.delete(authorFromDb);
        return "redirect:/authors";
    }
}
