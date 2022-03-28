package com.example.demo.service;

import com.example.demo.entity.SmallAvatar;
import com.example.demo.entity.SmallAvatarGroup;
import com.example.demo.entity.User;
import com.example.demo.entity.UserGroup;
import com.example.demo.exception.SmallAvatarGroupNotFoundException;
import com.example.demo.repository.SmallAvatarGroupRepository;
import com.example.demo.repository.UserGroupRepository;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class SmallAvatarGroupService {

    @Value("${upload.path.small.avatar}")
    private String uploadPath;

    public static final Logger LOG = LoggerFactory.getLogger(SmallAvatarUserService.class);

    private final SmallAvatarGroupRepository smallAvatarGroupRepository;
    private final UtilsService utilsService;
    private final UserGroupRepository userGroupRepository;
    private final UserGroupService userGroupService;

    public SmallAvatarGroupService(SmallAvatarGroupRepository smallAvatarGroupRepository,
                                   UtilsService utilsService,
                                   UserGroupRepository userGroupRepository,
                                   UserGroupService userGroupService) {
        this.smallAvatarGroupRepository = smallAvatarGroupRepository;
        this.utilsService = utilsService;
        this.userGroupRepository = userGroupRepository;
        this.userGroupService = userGroupService;
    }

    public SmallAvatarGroup getSmallAvatarGroupByGroup(UserGroup userGroup) {
        return smallAvatarGroupRepository.findByUserGroup(userGroup).orElse(null);
    }

    public SmallAvatarGroup getSmallAvatarGroupById (Long smallAvatarGroupId) {
        return smallAvatarGroupRepository.findById(smallAvatarGroupId)
                .orElseThrow(() -> new SmallAvatarGroupNotFoundException("Cannot be found group avatar image with id: " + smallAvatarGroupId));
    }

    public void saveSmallAvatarGroup(String fileAddress, Long userGroupId) {
        try {
            UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
            SmallAvatarGroup smallAvatarGroupFromDB;
            try {
                smallAvatarGroupFromDB = smallAvatarGroupRepository.findByUserGroup(userGroup).orElse(null);
                if (smallAvatarGroupFromDB != null) {
                    String pathToDelete = smallAvatarGroupFromDB.getAddress();
                    try {
                        // retrieve image
                        Files.delete(Path.of(pathToDelete));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    userGroup.setSmallAvatarGroup(null);
                    userGroupRepository.save(userGroup);
                    smallAvatarGroupRepository.delete(smallAvatarGroupFromDB);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
                File file = new File(fileAddress);
                BufferedImage img = ImageIO.read(file);
                BufferedImage smallImage;
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
                try {
                    // retrieve image
                    ImageIO.write(smallImage, "png", outputFile);
                } catch (IOException e) {
                }

                SmallAvatarGroup smallAvatarGroup = new SmallAvatarGroup();
                smallAvatarGroup.setName(file.getName());
                smallAvatarGroup.setAddress(uploadPath + "/" + file.getName());
                smallAvatarGroup.setUserGroup(userGroup);
                SmallAvatarGroup savedSmallAvatar = smallAvatarGroupRepository.save(smallAvatarGroup);
                userGroup.setSmallAvatarGroup(savedSmallAvatar);
                userGroupRepository.save(userGroup);

                LOG.info("Saving image for UseGroupr: {}", userGroup.getId());


        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
}
