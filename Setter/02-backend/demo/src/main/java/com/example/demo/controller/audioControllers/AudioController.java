package com.example.demo.controller.audioControllers;

import com.example.demo.dto.AudioFileDTO;
import com.example.demo.dto.NewStreamFileDTO;
import com.example.demo.entity.AudioFile;
import com.example.demo.entity.User;
import com.example.demo.exception.AudioFileNotFoundException;
import com.example.demo.facade.AudioFileFacade;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.service.UtilsService;
import com.example.demo.service.audioServices.AudioService;
import com.example.demo.validations.ResponseErrorValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/audio")
@RestController
public class AudioController {
    private final Logger logger = LoggerFactory.getLogger(AudioController.class);

    private final AudioService audioService;
    private final AudioFileFacade audioFileFacade;
    private final UtilsService utilsService;

    public AudioController(AudioService audioService,
                           AudioFileFacade audioFileFacade,
                           UtilsService utilsService
    ) {
        this.audioService = audioService;
        this.audioFileFacade = audioFileFacade;
        this.utilsService = utilsService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AudioFileDTO>> getAllAudioFiles(@RequestParam(required = false) String descriptionSearch,
                                                               @RequestParam(required = false) String page,
                                                               @RequestParam(required = false) String size,
                                                               Principal principal
    ) {
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
        User loginUser = utilsService.getUserByPrincipal(principal);
        List<AudioFile> audioFileList;
        if(descriptionSearch == null) {
            audioFileList = audioService.getAllAudioFiles(pageable);
        } else {
            audioFileList = audioService.searchAudioFiles(descriptionSearch, pageable);
        }
        List<AudioFileDTO> audioFileDTOList = new ArrayList<>();
        for (AudioFile audioFile : audioFileList) {
            audioFileDTOList.add(audioFileFacade.audioFileToAudioFileDTO(audioFile, loginUser));
        }
        return new ResponseEntity<>(audioFileDTOList, HttpStatus.OK);
    }

    @GetMapping("/count/all")
    public ResponseEntity<Long> countAllAudioFiles(@RequestParam(required = false) String descriptionSearch) throws IOException {
        Long postsAmount;
        if(descriptionSearch == null) {
            postsAmount = audioService.countAllAudioFiles();
        } else {
            postsAmount = audioService.countSearchAudioFiles(descriptionSearch);
        }
        return new ResponseEntity<>(postsAmount, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AudioFileDTO>> getAllAudioFilesFromUser(@PathVariable("userId") String userId,
                                                                       @RequestParam(required = false) String page,
                                                                       @RequestParam(required = false) String size,
                                                                       Principal principal
    ) {
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
        User loginUser = utilsService.getUserByPrincipal(principal);
        List<AudioFile> audioFileList = audioService.getAllAudioFilesFromUser(Long.valueOf(userId), pageable);
        List<AudioFileDTO> audioFileDTOList = new ArrayList<>();
        for (AudioFile audioFile : audioFileList) {
            audioFileDTOList.add(audioFileFacade.audioFileToAudioFileDTO(audioFile, loginUser));
        }
        return new ResponseEntity<>(audioFileDTOList, HttpStatus.OK);
    }

    @GetMapping("/user/count/{userId}")
    public ResponseEntity<Long> countAudioFilesForUserGroup(@PathVariable("userId") String userId) throws IOException {
        Long imageAmount = audioService.countAudioFilesToUser(Long.valueOf(userId));
        return new ResponseEntity<>(imageAmount, HttpStatus.OK);
    }

    @GetMapping("/group/{userGroupId}")
    public ResponseEntity<List<AudioFileDTO>> getAllAudioFilesFromUserGroup(@PathVariable("userGroupId") String userGroupId,
                                                                            @RequestParam(required = false) String page,
                                                                            @RequestParam(required = false) String size,
                                                                            Principal principal
    ) {
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
        User loginUser = utilsService.getUserByPrincipal(principal);
        List<AudioFile> audioFileList = audioService.getAllAudioFilesFromUserGroup(Long.valueOf(userGroupId), pageable);
        List<AudioFileDTO> audioFileDTOList = new ArrayList<>();
        for (AudioFile audioFile : audioFileList) {
            audioFileDTOList.add(audioFileFacade.audioFileToAudioFileDTO(audioFile, loginUser));
        }
        return new ResponseEntity<>(audioFileDTOList, HttpStatus.OK);
    }

    @GetMapping("/group/count/{userGroupId}")
    public ResponseEntity<Long> countAllAudioFilesForUserGroup(@PathVariable("userGroupId") String userGroupId) throws IOException {
        Long imageAmount = audioService.countAudioFilesToUserGroup(Long.valueOf(userGroupId));
        return new ResponseEntity<>(imageAmount, HttpStatus.OK);
    }

    @GetMapping("/{audioFileId}")
    public ResponseEntity<AudioFileDTO> findAudioFileById(@PathVariable("audioFileId") String audioFileId, Principal principal) {
        User loginUser = utilsService.getUserByPrincipal(principal);
        AudioFile audioFile = audioService.getAudioFileById(Long.valueOf(audioFileId));
        AudioFileDTO audioFileDTO = audioFileFacade.audioFileToAudioFileDTO(audioFile, loginUser);
        return new ResponseEntity<>(audioFileDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{audioFileId}")
    public ResponseEntity<MessageResponse> deleteAudioFile(@PathVariable("audioFileId") String audioFileId, Principal principal) {
        Boolean result = audioService.deleteAudioFile(Long.valueOf(audioFileId), principal);
        if (result) {
            return new ResponseEntity<>(new MessageResponse("AudioFile was deleted"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("AudioFile was not deleted, some error occurred"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/upload")
    public ResponseEntity<Long> uploadAudio(@RequestParam("description") String description,
                                            @RequestParam(required = false, name = "groupId") String groupId,
                                            @RequestParam("file") MultipartFile file,
                                            Principal principal
    ) {
        logger.info(description);
        Long savedAudioFileId;
        try {
            savedAudioFileId = audioService.saveNewAudio(description, groupId, file, principal);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return new ResponseEntity<>(savedAudioFileId, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<Void> notFoundExceptionHandler(AudioFileNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
