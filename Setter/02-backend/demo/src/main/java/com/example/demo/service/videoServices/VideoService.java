package com.example.demo.service.videoServices;


import com.example.demo.dto.NewStreamFileDTO;
import com.example.demo.entity.*;
import com.example.demo.exception.VideoFileNotFoundException;
import com.example.demo.repository.VideoFileRepository;
import com.example.demo.service.StreamBytesInfo;
import com.example.demo.service.UserGroupInviteService;
import com.example.demo.service.UserGroupService;
import com.example.demo.service.UtilsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRange;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static com.example.demo.service.UtilsService.removeFileExt;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

@Service
public class VideoService {
    private final Logger logger = LoggerFactory.getLogger(VideoService.class);

    @Value("${upload.path.videos}")
    private String dataFolder;

    private final VideoFileRepository videoFileRepository;
    private final FrameGrabberService frameGrabberService;
    private final UserGroupInviteService userGroupInviteService;
    private final UserGroupService userGroupService;
    private final UtilsService utilsService;

    @Autowired
    public VideoService(VideoFileRepository videoFileRepository,
                        FrameGrabberService frameGrabberService,
                        UserGroupInviteService userGroupInviteService,
                        UserGroupService userGroupService,
                        UtilsService utilsService
    ) {
        this.videoFileRepository = videoFileRepository;
        this.frameGrabberService = frameGrabberService;
        this.userGroupInviteService = userGroupInviteService;
        this.userGroupService = userGroupService;
        this.utilsService = utilsService;
    }
    public VideoFile getVideoFileById (Long videoFileId) {
        return videoFileRepository.findById(videoFileId)
                .orElseThrow(() -> new VideoFileNotFoundException());
    }

    public List<VideoFile> getAllVideoFiles(Pageable pageable) {
        return videoFileRepository.findAllByOrderByCreatedDateDesc(pageable);
    }
    public Long countAllVideoFiles() {return videoFileRepository.count();}

    public List<VideoFile> searchVideoFiles(String searchValue, Pageable pageable) {
        return videoFileRepository.findByDescriptionContainingOrderByCreatedDateDesc(searchValue, pageable);
    }
    public Long countSearchVideoFiles(String searchValue) {
        return videoFileRepository.countByDescriptionContaining(searchValue);
    }

    public  List<VideoFile> getAllVideoFilesFromUser(Long userId, Pageable pageable) {
        User user = utilsService.getUserById(userId);
        return videoFileRepository.findAllByUserAndUserGroupOrderByCreatedDateDesc(user, null, pageable);
    }

    public Long countVideoFilesToUser(Long userId) {
        User user = utilsService.getUserById(userId);
        if (user == null) {
            return null;
        } else {
            return videoFileRepository.countByUserAndUserGroup(user, null);
        }
    }

    public  List<VideoFile> getAllVideoFilesFromUserGroup(Long userGroupId, Pageable pageable) {
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        return videoFileRepository.findAllByUserGroupOrderByCreatedDateDesc(userGroup, pageable);
    }

    public Long countVideoFilesToUserGroup(Long userGroupId) {
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        if (userGroup == null) {
            return null;
        } else {
            return videoFileRepository.countByUserGroup(userGroup);
        }
    }

    @Transactional
    public Long saveNewVideo(String description, String groupId, MultipartFile fileIn, MultipartFile filePreview, Principal principal) {
        VideoFile videoFile = new VideoFile();
        User user = utilsService.getUserByPrincipal(principal);
        videoFile.setUser(user);

        if (groupId != null) {
            try {
                UserGroup userGroup = userGroupService.getUserGroupById(Long.valueOf(groupId));
                videoFile.setUserGroup(userGroup);
            } catch (Exception ex) {
                //ex.printStackTrace();
            }
        }

        videoFile.setFileName(fileIn.getOriginalFilename());
        videoFile.setContentType(fileIn.getContentType());
        videoFile.setFileSize(fileIn.getSize());
        videoFile.setDescription(description);
        videoFile.setLikes(0);
        VideoFile savedVideoFile = videoFileRepository.save(videoFile);

        Path directory = Path.of(dataFolder, savedVideoFile.getId().toString());

        try {
            Files.createDirectory(directory);
            Path file = Path.of(directory.toString(), fileIn.getOriginalFilename());
            try (OutputStream output = Files.newOutputStream(file, CREATE, WRITE)) {
                fileIn.getInputStream().transferTo(output);
            }
            long videoLength = frameGrabberService.generatePreviewPictures(file, filePreview);
            videoFile.setVideoLength(videoLength);
            savedVideoFile = videoFileRepository.save(videoFile);
        } catch (IOException ex) {
            logger.error("", ex);
            ex.printStackTrace();
            throw new IllegalStateException(ex);
        }
        return savedVideoFile.getId();
    }

    public Optional<InputStream> getPreviewInputStream(Long id) {
        return videoFileRepository.findById(id)
                .flatMap(vmd -> {
                    Path previewPicturePath = Path.of(dataFolder,
                            vmd.getId().toString(),
                            removeFileExt(vmd.getFileName()) + ".jpeg");
                    if (!Files.exists(previewPicturePath)) {
                        return Optional.empty();
                    }
                    try {
                        return Optional.of(Files.newInputStream(previewPicturePath));
                    } catch (IOException ex) {
                        logger.error("", ex);
                        return Optional.empty();
                    }
        });
    }

    public Optional<StreamBytesInfo> getStreamBytes(Long id, HttpRange range) {
        Optional<VideoFile> byId = videoFileRepository.findById(id);
        if (byId.isEmpty()) {
            return Optional.empty();
        }
        Path filePath = Path.of(dataFolder, Long.toString(id), byId.get().getFileName());
        if (!Files.exists(filePath)) {
            logger.error("File {} not found", filePath);
            return Optional.empty();
        }
        try {
            long fileSize = Files.size(filePath);
            long chunkSize = fileSize / 100;
            if (range == null) {
                return Optional.of(new StreamBytesInfo(
                        out -> Files.newInputStream(filePath).transferTo(out),
                        fileSize, 0, fileSize, byId.get().getContentType()));
            }

            long rangeStart = range.getRangeStart(0);
            long rangeEnd = rangeStart + chunkSize; // range.getRangeEnd(fileSize);
            if (rangeEnd >= fileSize) {
                rangeEnd = fileSize - 1;
            }
            long finalRangeEnd = rangeEnd;
            return Optional.of(new StreamBytesInfo(
                    out -> {
                        try (InputStream inputStream = Files.newInputStream(filePath)) {
                            inputStream.skip(rangeStart);
                            byte[] bytes = inputStream.readNBytes((int) ((finalRangeEnd - rangeStart) + 1));
                            out.write(bytes);
                        }
                    },
                    fileSize, rangeStart, rangeEnd, byId.get().getContentType()));
        } catch (IOException ex) {
            logger.error("", ex);
            return Optional.empty();
        }
    }


    public boolean isUserAuthor(VideoFile videoFile, User user) {
        UserGroup userGroup = videoFile.getUserGroup();
        UserGroupPair userGroupPair = userGroupInviteService.getUserGroupPairByUserAndGroup(user, userGroup);
        if (userGroupPair != null) {
            return userGroupPair.getIsAdmin();
        } else {
            return user.getId().equals(videoFile.getUser().getId());
        }
    }

    public Boolean deleteVideoFile(Long videoFileId, Principal principal) {
        System.out.println("public Boolean deleteVideoFile(Long videoFileId, Principal principal) {");
        VideoFile videoFile = videoFileRepository.findById(videoFileId).orElse(null);
        if (videoFile == null) {
            return false;
        }
        User user = utilsService.getUserByPrincipal(principal);
        if (!isUserAuthor(videoFile, user)) {
            return false;
        }
        //Path filePath = Path.of(dataFolder, Long.toString(videoFile.getId()));
        File file = new File(dataFolder + "/" + videoFile.getId());
        utilsService.deleteDir(file);
        videoFileRepository.delete(videoFile);
        return true;
    }
}
