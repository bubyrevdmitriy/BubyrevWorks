package com.example.demo.service;

import com.example.demo.entity.SmallAvatar;
import com.example.demo.entity.User;
import com.example.demo.exception.SmallAvatarNotFoundException;
import com.example.demo.repository.SmallAvatarRepository;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;

@Service
public class SmallAvatarUserService {

    @Value("${upload.path.small.avatar}")
    private String uploadPath;

    public static final Logger LOG = LoggerFactory.getLogger(SmallAvatarUserService.class);

    private final SmallAvatarRepository smallAvatarRepository;
    private final UtilsService utilsService;
    private final UserRepository userRepository;

    @Autowired
    public SmallAvatarUserService(SmallAvatarRepository smallAvatarRepository,
                                  UtilsService utilsService,
                                  UserRepository userRepository
    ) {
        this.smallAvatarRepository = smallAvatarRepository;
        this.utilsService = utilsService;
        this.userRepository = userRepository;
    }


    public SmallAvatar getSmallAvatarByUser(User user) {
        return smallAvatarRepository.findByUser(user).orElse(null);
    }

    public SmallAvatar getSmallAvatarById (Long smallAvatarId) {
        return smallAvatarRepository.findById(smallAvatarId)
                .orElseThrow(() -> new SmallAvatarNotFoundException("Cannot be found avatar image with id: " + smallAvatarId));
    }

    public void saveSmallAvatar(String fileAddress, Principal principal) {
        try {
            System.out.println(fileAddress);
            User user = utilsService.getUserByPrincipal(principal);
            SmallAvatar smallAvatarFromDB;
            try {
                smallAvatarFromDB= smallAvatarRepository.findByUser(user).orElse(null);
                if (smallAvatarFromDB != null) {
                    String pathToDelete = smallAvatarFromDB.getAddress();
                    try {
                        // retrieve image
                        Files.delete(Path.of(pathToDelete));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    user.setSmallAvatar(null);
                    userRepository.save(user);
                    smallAvatarRepository.delete(smallAvatarFromDB);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            File file = new File(fileAddress);

            try {
                BufferedImage img = ImageIO.read(file);
                BufferedImage smallImage;
                /*if (img.getWidth()>300) {
                    //smallImage = img;
                    smallImage = utilsService.scale(img,300, (int) (img.getHeight()*(300.0/img.getWidth())));
                } else {
                    smallImage = img;
                }*/
                if (img.getWidth() > 300 && img.getHeight() > 300) {
                    smallImage = utilsService.scale(img, 300, (int) (img.getHeight() * (300.0 / img.getWidth())));
                } else if (img.getWidth() > 300) {
                    smallImage = utilsService.scale(img, 300, (int) (img.getHeight() * (300.0 / img.getWidth())));
                } else if (img.getHeight() > 300) {
                    smallImage = utilsService.scale(img, (int) (img.getWidth() * (300.0 / img.getHeight())), 300);
                } else {
                    smallImage = img;
                }


                File outputFile = new File(uploadPath + "/" + file.getName());
                // retrieve image
                ImageIO.write(smallImage, "png", outputFile);


            } catch (Exception e) {
                e.printStackTrace();
            }

            SmallAvatar smallAvatar = new SmallAvatar();
            smallAvatar.setName(file.getName());
            smallAvatar.setAddress(uploadPath + "/" + file.getName());
            smallAvatar.setUser(user);
            SmallAvatar savedSmallAvatar = smallAvatarRepository.save(smallAvatar);
            System.out.println("---------------------------------------------");
            user.setSmallAvatar(savedSmallAvatar);
            userRepository.save(user);

            LOG.info("Saving image for User: {}", user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
