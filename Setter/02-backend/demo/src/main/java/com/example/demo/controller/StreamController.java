package com.example.demo.controller;

import com.example.demo.exception.AudioFileNotFoundException;
import com.example.demo.exception.VideoFileNotFoundException;
import com.example.demo.service.StreamBytesInfo;
import com.example.demo.service.UtilsService;
import com.example.demo.service.audioServices.AudioService;
import com.example.demo.service.videoServices.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;


import java.text.DecimalFormat;
import java.util.List;

@RequestMapping("/api/stream")
@RestController
public class StreamController {

    private final Logger logger = LoggerFactory.getLogger(StreamController.class);

    private final AudioService audioService;
    private final VideoService videoService;
    private final UtilsService utilsService;

    @Autowired
    public StreamController(AudioService audioService,
                            VideoService videoService,
                            UtilsService utilsService
    ) {
        this.audioService = audioService;
        this.videoService = videoService;
        this.utilsService = utilsService;
    }


    @GetMapping("/audioStream/{id}")
    public ResponseEntity<StreamingResponseBody> streamAudio(@RequestHeader(value = "Range", required = false) String httpRangeHeader,
                                                             @PathVariable("id") Long id, @CookieValue("auth-token") String auth_token) {
        logger.info("Requested range [{}] for file `{}`", httpRangeHeader, id);
        String token = utilsService.getTokenFromString(auth_token);
        System.out.println(token);
        boolean isTokenCorrect = utilsService.validateToken(token);
        if (isTokenCorrect) {
            List<HttpRange> httpRangeList = HttpRange.parseRanges(httpRangeHeader);
            StreamBytesInfo streamBytesInfo = audioService.getStreamBytes(id, httpRangeList.size() > 0 ? httpRangeList.get(0) : null)
                    .orElseThrow(() -> new AudioFileNotFoundException("Cannot find VideoFile with id: " + id));

            long byteLength = streamBytesInfo.getRangeEnd() - streamBytesInfo.getRangeStart() + 1;
            ResponseEntity.BodyBuilder builder = ResponseEntity.status(httpRangeList.size() > 0 ? HttpStatus.PARTIAL_CONTENT : HttpStatus.OK)
                    .header("Content-Type", streamBytesInfo.getContentType())
                    .header("Accept-Ranges", "bytes")
                    .header("Content-Length", Long.toString(byteLength));

            if (httpRangeList.size() > 0) {
                builder.header("Content-Range",
                        "bytes " + streamBytesInfo.getRangeStart() +
                                "-" + streamBytesInfo.getRangeEnd() +
                                "/" + streamBytesInfo.getFileSize());
            }
            logger.info("Providing bytes from {} to {}. We are at {}% of overall video.",
                    streamBytesInfo.getRangeStart(), streamBytesInfo.getRangeEnd(),
                    new DecimalFormat("###.##").format(100.0 * streamBytesInfo.getRangeStart() / streamBytesInfo.getFileSize()));
            return builder.body(streamBytesInfo.getResponseBody());
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

    }


    @GetMapping("/videoStream/{id}")
    public ResponseEntity<StreamingResponseBody> streamVideo(@RequestHeader(value = "Range", required = false) String httpRangeHeader,
                                                             @PathVariable("id") Long id, @CookieValue("auth-token") String auth_token) {
        logger.info("Requested range [{}] for file `{}`", httpRangeHeader, id);
        String token = utilsService.getTokenFromString(auth_token);
        System.out.println(token);
        boolean isTokenCorrect = utilsService.validateToken(token);
        if (isTokenCorrect) {
            List<HttpRange> httpRangeList = HttpRange.parseRanges(httpRangeHeader);
            StreamBytesInfo streamBytesInfo = videoService.getStreamBytes(id, httpRangeList.size() > 0 ? httpRangeList.get(0) : null)
                    .orElseThrow(() ->new VideoFileNotFoundException());

            long byteLength = streamBytesInfo.getRangeEnd() - streamBytesInfo.getRangeStart() + 1;
            ResponseEntity.BodyBuilder builder = ResponseEntity.status(httpRangeList.size() > 0 ? HttpStatus.PARTIAL_CONTENT : HttpStatus.OK)
                    .header("Content-Type", streamBytesInfo.getContentType())
                    .header("Accept-Ranges", "bytes")
                    .header("Content-Length", Long.toString(byteLength));

            if (httpRangeList.size() > 0) {
                builder.header("Content-Range",
                        "bytes " + streamBytesInfo.getRangeStart() +
                                "-" + streamBytesInfo.getRangeEnd() +
                                "/" + streamBytesInfo.getFileSize());
            }
            logger.info("Providing bytes from {} to {}. We are at {}% of overall video.",
                    streamBytesInfo.getRangeStart(), streamBytesInfo.getRangeEnd(),
                    new DecimalFormat("###.##").format(100.0 * streamBytesInfo.getRangeStart() / streamBytesInfo.getFileSize()));
            return builder.body(streamBytesInfo.getResponseBody());
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

    }

    /*
    private String getTokenFromString(String bearToken) {
        // get JWTToken from head of request, that we have from frontend to server
        //String bearToken = request.getHeader(SecurityConstants.HEADER_STRING);
        if (StringUtils.hasText(bearToken) && bearToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            // space from TOKEN_PREFIX
            //return bearToken.split(" ")[1];
            //a = a.replace("{bbbbbb}", "");
            return bearToken.replace(SecurityConstants.TOKEN_PREFIX,"");
        }
        return null;
    }


    public boolean validateToken(String token) {
        // if token is ok return true? if some error occured return false
        try {
            Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(token);
            return true;
        }catch (SignatureException |
                MalformedJwtException |
                ExpiredJwtException |
                UnsupportedJwtException |
                IllegalArgumentException ex) {
            logger.error(ex.getMessage());
            return false;
        }
    }
     */
}
