package com.bubyrevdmitriygmail.LibraryBubyrev.service;

import com.bubyrevdmitriygmail.LibraryBubyrev.domain.Author;
import com.bubyrevdmitriygmail.LibraryBubyrev.repos.AuthorRepo;
import com.bubyrevdmitriygmail.LibraryBubyrev.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class AuthorService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private MailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${hostname}")
    private String hostname;

    @Value("${upload.path}")
    private String uploadPath;

    public void addNewAuthor(Author author, MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile +"."+file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));
            author.setFilename(resultFilename);
        }
        authorRepo.save(author);
    }

    public void updateAuthor(Author author, MultipartFile file) throws IOException {
        Author authorFromDb = authorRepo.findAuthorById(author.getId());
        authorFromDb.setFirstName(author.getFirstName());
        authorFromDb.setLastName(author.getLastName());
        authorFromDb.setMiddleName(author.getMiddleName());
        authorFromDb.setTag(author.getTag());

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile +"."+file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));
            authorFromDb.setFilename(resultFilename);
        }
        authorRepo.save(author);
    }






}
