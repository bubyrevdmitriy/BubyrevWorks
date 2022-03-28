package com.example.demo.controller.videoControllers;

import com.example.demo.dto.NewStreamFileDTO;
import com.example.demo.dto.VideoFileDTO;
import com.example.demo.entity.User;
import com.example.demo.entity.VideoFile;
import com.example.demo.exception.VideoFileNotFoundException;
import com.example.demo.exception.VideoPreviewNotFoundException;
import com.example.demo.facade.VideoFileFacade;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.repository.VideoFileRepository;
import com.example.demo.service.UtilsService;
import com.example.demo.service.videoServices.VideoService;
import com.example.demo.validations.ResponseErrorValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/video")
@RestController
public class VideoController {
    private final Logger logger = LoggerFactory.getLogger(VideoController.class);

    private final VideoService videoService;
    private final VideoFileFacade videoFileFacade;
    private final UtilsService utilsService;

    @Value("${upload.path.videos}")
    private String dataFolder;

    @Autowired
    public VideoController(VideoService videoService,
                           VideoFileFacade videoFileFacade,
                           UtilsService utilsService
    ) {
        this.videoService = videoService;
        this.videoFileFacade = videoFileFacade;
        this.utilsService = utilsService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<VideoFileDTO>> getAllVideoFiles(@RequestParam(required = false) String descriptionSearch,
                                                               @RequestParam(required = false) String page,
                                                               @RequestParam(required = false) String size,
                                                               Principal principal
    ) {
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
        User loginUser = utilsService.getUserByPrincipal(principal);
        List<VideoFile> videoFileList;
        if(descriptionSearch == null) {
            videoFileList = videoService.getAllVideoFiles(pageable);
        } else {
            videoFileList = videoService.searchVideoFiles(descriptionSearch, pageable);
        }
        List<VideoFileDTO> videoFileDTOList = new ArrayList<>();
        for (VideoFile videoFile : videoFileList) {
            videoFileDTOList.add(videoFileFacade.videoFileToVideoFileDTO(videoFile, loginUser));
        }
        return new ResponseEntity<>(videoFileDTOList, HttpStatus.OK);
    }

    @GetMapping("/count/all")
    public ResponseEntity<Long> countAllVideoFiles(@RequestParam(required = false) String descriptionSearch) throws IOException {
        Long postsAmount;
        if(descriptionSearch == null) {
            postsAmount = videoService.countAllVideoFiles();
        } else {
            postsAmount = videoService.countSearchVideoFiles(descriptionSearch);
        }
        return new ResponseEntity<>(postsAmount, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<VideoFileDTO>> getAllVideoFilesFromUser(@PathVariable("userId") String userId,
                                                                       @RequestParam(required = false) String page,
                                                                       @RequestParam(required = false) String size,
                                                                       Principal principal
    ) {
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
        User loginUser = utilsService.getUserByPrincipal(principal);
        List<VideoFile> videoFileList = videoService.getAllVideoFilesFromUser(Long.valueOf(userId), pageable);
        List<VideoFileDTO> videoFileDTOList = new ArrayList<>();
        for (VideoFile videoFile : videoFileList) {
            videoFileDTOList.add(videoFileFacade.videoFileToVideoFileDTO(videoFile, loginUser));
        }
        return new ResponseEntity<>(videoFileDTOList, HttpStatus.OK);
    }

    @GetMapping("/user/count/{userId}")
    public ResponseEntity<Long> countVideoFilesForUserGroup(@PathVariable("userId") String userId) throws IOException {
        Long imageAmount = videoService.countVideoFilesToUser(Long.valueOf(userId));
        return new ResponseEntity<>(imageAmount, HttpStatus.OK);
    }

    @GetMapping("/group/{userGroupId}")
    public ResponseEntity<List<VideoFileDTO>> getAllVideoFilesFromUserGroup(@PathVariable("userGroupId") String userGroupId,
                                                                            @RequestParam(required = false) String page,
                                                                            @RequestParam(required = false) String size,
                                                                            Principal principal
    ) {
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
        User loginUser = utilsService.getUserByPrincipal(principal);
        List<VideoFile> videoFileList = videoService.getAllVideoFilesFromUserGroup(Long.valueOf(userGroupId), pageable);
        List<VideoFileDTO> videoFileDTOList = new ArrayList<>();
        for (VideoFile videoFile : videoFileList) {
            videoFileDTOList.add(videoFileFacade.videoFileToVideoFileDTO(videoFile, loginUser));
        }
        return new ResponseEntity<>(videoFileDTOList, HttpStatus.OK);
    }

    @GetMapping("/group/count/{userGroupId}")
    public ResponseEntity<Long> countAllVideoFilesForUserGroup(@PathVariable("userGroupId") String userGroupId) throws IOException {
        Long imageAmount = videoService.countVideoFilesToUserGroup(Long.valueOf(userGroupId));
        return new ResponseEntity<>(imageAmount, HttpStatus.OK);
    }

    @GetMapping("/{videoFileId}")
    public ResponseEntity<VideoFileDTO> findVideoFileById(@PathVariable("videoFileId") String videoFileId, Principal principal) {
        User loginUser = utilsService.getUserByPrincipal(principal);
        VideoFile videoFile = videoService.getVideoFileById(Long.valueOf(videoFileId));
        VideoFileDTO videoFileDTO = videoFileFacade.videoFileToVideoFileDTO(videoFile, loginUser);
        return new ResponseEntity<>(videoFileDTO, HttpStatus.OK);
    }


    @DeleteMapping("/{videoFileId}")
    public ResponseEntity<MessageResponse> deleteVideoFile(@PathVariable("videoFileId") String videoFileId, Principal principal) {
        Boolean result = videoService.deleteVideoFile(Long.valueOf(videoFileId), principal);
        if (result) {
            return new ResponseEntity<>(new MessageResponse("VideoFile was deleted"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("VideoFile was not deleted, some error occurred"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/preview/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<StreamingResponseBody> getPreviewPicture(@PathVariable("id") Long id, Principal principal) {
        InputStream inputStream = videoService.getPreviewInputStream(id)
                .orElseThrow(VideoPreviewNotFoundException::new);
        return ResponseEntity.ok(inputStream::transferTo);
    }

    @PostMapping(path = "/upload")
    public ResponseEntity<Long> uploadVideo(@RequestParam("description") String description,
                                            @RequestParam(required = false, name = "groupId") String groupId,
                                            @RequestParam("file") MultipartFile file,
                                            @RequestParam(required = false, name = "filePreview") MultipartFile filePreview,
                                            Principal principal
    ) {
        System.out.println("public ResponseEntity<Long> uploadVideo(@RequestParam(\"description\") String description");
        System.out.println(filePreview);

        if (filePreview==null) {
            System.out.println("filePreview==null");
        } else {
            System.out.println("filePreview!=null");
        }

        logger.info(description);
        Long savedVideoFileId;
        try {
            savedVideoFileId = videoService.saveNewVideo(description, groupId, file, filePreview, principal);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return new ResponseEntity<>(savedVideoFileId, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<Void> notFoundExceptionHandler(VideoPreviewNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
