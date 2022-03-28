package com.example.demo.service.audioServices;

import com.example.demo.dto.NewStreamFileDTO;
import com.example.demo.entity.*;
import com.example.demo.exception.AudioFileNotFoundException;
import com.example.demo.repository.AudioFileRepository;
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
import org.springframework.web.bind.annotation.RequestBody;
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

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

@Service
public class AudioService {
    private final Logger logger = LoggerFactory.getLogger(AudioService.class);

    @Value("${upload.path.audios}")
    private String dataFolder;

    private final AudioFileRepository audioFileRepository;
    private final UserGroupInviteService userGroupInviteService;
    private final UserGroupService userGroupService;
    private final UtilsService utilsService;

    @Autowired
    public AudioService(AudioFileRepository audioFileRepository,
                        UserGroupInviteService userGroupInviteService,
                        UserGroupService userGroupService, UtilsService utilsService) {
        this.audioFileRepository = audioFileRepository;
        this.userGroupInviteService = userGroupInviteService;
        this.userGroupService = userGroupService;
        this.utilsService = utilsService;
    }

    public AudioFile getAudioFileById (Long audioFileId) {
        return audioFileRepository.findById(audioFileId)
                .orElseThrow(() -> new AudioFileNotFoundException("Cannot find AudioFile with id: " + audioFileId));
    }

    public List<AudioFile> getAllAudioFiles(Pageable pageable) {
        return audioFileRepository.findAllByOrderByCreatedDateDesc(pageable);
    }
    public Long countAllAudioFiles() {return audioFileRepository.count();}

    public List<AudioFile> searchAudioFiles(String searchValue, Pageable pageable) {
        return audioFileRepository.findByDescriptionContainingOrderByCreatedDateDesc(searchValue, pageable);
    }
    public Long countSearchAudioFiles(String searchValue) {
        return audioFileRepository.countByDescriptionContaining(searchValue);
    }

    public  List<AudioFile> getAllAudioFilesFromUser(Long userId, Pageable pageable) {
        User user = utilsService.getUserById(userId);
        return audioFileRepository.findAllByUserAndUserGroupOrderByCreatedDateDesc(user, null, pageable);
    }

    public Long countAudioFilesToUser(Long userId) {
        User user = utilsService.getUserById(userId);
        if (user == null) {
            return null;
        } else {
            return audioFileRepository.countByUserAndUserGroup(user, null);
        }
    }

    public  List<AudioFile> getAllAudioFilesFromUserGroup(Long userGroupId, Pageable pageable) {
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        return audioFileRepository.findAllByUserGroupOrderByCreatedDateDesc(userGroup, pageable);
    }

    public Long countAudioFilesToUserGroup(Long userGroupId) {
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        if (userGroup == null) {
            return null;
        } else {
            return audioFileRepository.countByUserGroup(userGroup);
        }
    }

    @Transactional
    public long saveNewAudio(String description, String groupId, MultipartFile fileIn, Principal principal) {
        AudioFile audioFile = new AudioFile();
        User user = utilsService.getUserByPrincipal(principal);
        audioFile.setUser(user);
        if (groupId != null) {
            try {
                UserGroup userGroup = userGroupService.getUserGroupById(Long.valueOf(groupId));
                audioFile.setUserGroup(userGroup);
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
        audioFile.setFileName(fileIn.getOriginalFilename());
        audioFile.setContentType(fileIn.getContentType());
        audioFile.setFileSize(fileIn.getSize());
        audioFile.setDescription(description);
        audioFile.setLikes(0);
        AudioFile savedAudioFile = audioFileRepository.save(audioFile);

        Path directory = Path.of(dataFolder, audioFile.getId().toString());
        try {
            Files.createDirectory(directory);
            Path file = Path.of(directory.toString(), fileIn.getOriginalFilename());
            try (OutputStream output = Files.newOutputStream(file, CREATE, WRITE)) {
                fileIn.getInputStream().transferTo(output);
            }
            //savedAudioFile = audioFileRepository.save(audioFile);
        } catch (IOException ex) {
            logger.error("", ex);
            throw new IllegalStateException(ex);
        }
        return savedAudioFile.getId();
    }

    /*
     @Transactional
    public Long saveNewVideo(String description, String groupId, MultipartFile fileIn, Principal principal) {
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
            long videoLength = frameGrabberService.generatePreviewPictures(file);
            videoFile.setVideoLength(videoLength);
            savedVideoFile = videoFileRepository.save(videoFile);
        } catch (IOException ex) {
            logger.error("", ex);
            ex.printStackTrace();
            throw new IllegalStateException(ex);
        }
        return savedVideoFile.getId();
    }
    */

    public Optional<StreamBytesInfo> getStreamBytes(Long id, HttpRange range) {
        Optional<AudioFile> byId = audioFileRepository.findById(id);
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


    public boolean isUserAuthor(AudioFile audioFile, User user) {
        UserGroup userGroup = audioFile.getUserGroup();
        UserGroupPair userGroupPair = userGroupInviteService.getUserGroupPairByUserAndGroup(user, userGroup);
        if (userGroupPair != null) {
            return userGroupPair.getIsAdmin();
        } else {
            return user.getId().equals(audioFile.getUser().getId());
        }
    }

    public Boolean deleteAudioFile(Long valueOf, Principal principal) {
        System.out.println("public Boolean deleteAudioFile(Long valueOf, Principal principal) {");
        AudioFile audioFile = audioFileRepository.findById(valueOf).orElse(null);
        if (audioFile == null) {
            return false;
        }
        User user = utilsService.getUserByPrincipal(principal);
        if (!isUserAuthor(audioFile, user)) {
            return false;
        }
        File file = new File(dataFolder + "/" + audioFile.getId());
        utilsService.deleteDir(file);
        audioFileRepository.delete(audioFile);
        return true;
    }
}
