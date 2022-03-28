package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.audioServices.AudioService;
import com.example.demo.service.videoServices.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class UserLikeService {
    public static final Logger LOG = LoggerFactory.getLogger(UserLikeService.class);

    private final PostService postService;
    private final CommentService commentService;
    private final CommonImageService commonImageService;
    private final AudioService audioService;
    private final VideoService videoService;
    private final UserLikeRepository userLikeRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final CommonImageRepository commonImageRepository;
    private final AudioFileRepository audioFileRepository;
    private final VideoFileRepository videoFileRepository;
    private final UtilsService utilsService;

    @Autowired
    public UserLikeService(PostService postService,
                           CommentService commentService,
                           CommonImageService commonImageService,
                           AudioService audioService,
                           VideoService videoService,
                           UserLikeRepository userLikeRepository,
                           PostRepository postRepository,
                           CommentRepository commentRepository,
                           CommonImageRepository commonImageRepository,
                           AudioFileRepository audioFileRepository,
                           VideoFileRepository videoFileRepository,
                           UtilsService utilsService
    ) {
        this.postService = postService;
        this.commentService = commentService;
        this.commonImageService = commonImageService;
        this.audioService = audioService;
        this.videoService = videoService;
        this.userLikeRepository = userLikeRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.commonImageRepository = commonImageRepository;
        this.audioFileRepository = audioFileRepository;
        this.videoFileRepository = videoFileRepository;
        this.utilsService = utilsService;
    }

     public Boolean likePost(Long postId, Principal principal) {
         try {
             User user = utilsService.getUserByPrincipal(principal);
             Post post = postService.getPostById(postId);
             if (!isUserLikePost(user, post)) {
                 UserLike userLike = new UserLike();
                 userLike.setUser(user);
                 userLike.setPost(post);
                 if (post.getLikes() == null) {
                     post.setLikes(1);
                 } else {
                     post.setLikes(post.getLikes() + 1);
                 }
                 postRepository.save(post);
                 userLikeRepository.save(userLike);
                 LOG.info("Saving Like for User: {}. And post: {}", user.getId(), post.getId());
                 return true;
             } else {
                 return false;
             }
         } catch (Exception e) {
             return false;
         }
     }

    public Boolean likeComment(Long commentId, Principal principal) {
        try {
            User user = utilsService.getUserByPrincipal(principal);
            Comment comment = commentService.getCommentById(commentId);
            if (!isUserLikeComment(user, comment)) {
                UserLike userLike = new UserLike();
                userLike.setUser(user);
                userLike.setComment(comment);
                if (comment.getLikes() == null) {
                    comment.setLikes(1);
                } else {
                    comment.setLikes(comment.getLikes() + 1);
                }
                commentRepository.save(comment);
                userLikeRepository.save(userLike);
                LOG.info("Saving Like for User: {}. And Comment: {}", user.getId(), comment.getId());
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean likeCommonImage(Long commonImageId, Principal principal) {
        try {
            User user = utilsService.getUserByPrincipal(principal);
            CommonImage commonImage = commonImageService.getCommonImageById(commonImageId);
            if (!isUserLikeCommonImage(user, commonImage)) {
                UserLike userLike = new UserLike();
                userLike.setUser(user);
                userLike.setCommonImage(commonImage);
                if (commonImage.getLikes() == null) {
                    commonImage.setLikes(1);
                } else {
                    commonImage.setLikes(commonImage.getLikes() + 1);
                }
                commonImageRepository.save(commonImage);
                userLikeRepository.save(userLike);
                LOG.info("Saving Like for User: {}. And commonImage: {}", user.getId(), commonImage.getId());
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean likeAudioFile(Long audioFileId, Principal principal) {
        try {
            User user = utilsService.getUserByPrincipal(principal);
            AudioFile audioFile = audioService.getAudioFileById(audioFileId);
            if (!isUserLikeAudioFile(user, audioFile)) {
                UserLike userLike = new UserLike();
                userLike.setUser(user);
                userLike.setAudioFile(audioFile);
                if (audioFile.getLikes() == null) {
                    audioFile.setLikes(1);
                } else {
                    audioFile.setLikes(audioFile.getLikes() + 1);
                }
                audioFileRepository.save(audioFile);
                userLikeRepository.save(userLike);
                LOG.info("Saving Like for User: {}. And audioFile: {}", user.getId(), audioFile.getId());
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean likeVideoFile(Long videoFileId, Principal principal) {
        try {
            User user = utilsService.getUserByPrincipal(principal);
            VideoFile videoFile = videoService.getVideoFileById(videoFileId);
            if (!isUserLikeVideoFile(user, videoFile)) {
                UserLike userToVideoFileLike = new UserLike();
                userToVideoFileLike.setUser(user);
                userToVideoFileLike.setVideoFile(videoFile);
                if (videoFile.getLikes() == null) {
                    videoFile.setLikes(1);
                } else {
                    videoFile.setLikes(videoFile.getLikes() + 1);
                }
                videoFileRepository.save(videoFile);
                userLikeRepository.save(userToVideoFileLike);
                LOG.info("Saving Like for User: {}. And videoFile: {}", user.getId(), videoFile.getId());
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }




    public Boolean disLikePost(Long postId, Principal principal) {
         try {
             User user = utilsService.getUserByPrincipal(principal);
             Post post = postService.getPostById(postId);
             if (isUserLikePost(user, post)) {
                 UserLike userToPostLike = getUserLikeByUserAndPost(user, post);
                 if (userToPostLike != null) {
                     post.setLikes(post.getLikes() - 1);
                     postRepository.save(post);
                     userLikeRepository.delete(userToPostLike);
                     LOG.info("Delete Like for User: {}. And post: {}", user.getId(), post.getId());
                     return true;
                 }
             } else {
                 return false;
             }
         } catch (Exception e) {
         }
        return false;
    }

    public Boolean disLikeComment(Long commentId, Principal principal) {
        try {
            User user = utilsService.getUserByPrincipal(principal);
            Comment comment = commentService.getCommentById(commentId);
            if (isUserLikeComment(user, comment)) {
                UserLike userToCommentLike = getUserLikeByUserAndComment(user, comment);
                if (userToCommentLike != null) {
                    comment.setLikes(comment.getLikes() - 1);
                    commentRepository.save(comment);
                    userLikeRepository.delete(userToCommentLike);
                    LOG.info("Delete Like for User: {}. And Comment: {}", user.getId(), comment.getId());
                    return true;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public Boolean disLikeCommonImage(Long commonImageId, Principal principal) {
        try {
            User user = utilsService.getUserByPrincipal(principal);
            CommonImage commonImage = commonImageService.getCommonImageById(commonImageId);
            if (isUserLikeCommonImage(user, commonImage)) {
                UserLike userToCommonImageLike = getUserLikeByUserAndCommonImage(user, commonImage);
                if (userToCommonImageLike != null) {
                    commonImage.setLikes(commonImage.getLikes() - 1);
                    commonImageRepository.save(commonImage);
                    userLikeRepository.delete(userToCommonImageLike);
                    LOG.info("Delete Like for User: {}. And commonImage: {}", user.getId(), commonImage.getId());
                    return true;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public Boolean disLikeAudioFile(Long audioFileId, Principal principal) {
        try {
            User user = utilsService.getUserByPrincipal(principal);
            AudioFile audioFile = audioService.getAudioFileById(audioFileId);
            if (isUserLikeAudioFile(user, audioFile)) {
                UserLike userLike = getUserLikeByUserAndAudioFile(user, audioFile);
                if (userLike != null) {
                    audioFile.setLikes(audioFile.getLikes() - 1);
                    audioFileRepository.save(audioFile);
                    userLikeRepository.delete(userLike);
                    LOG.info("Delete Like for User: {}. audioFile: {}", user.getId(), audioFile.getId());
                    return true;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public Boolean disLikeVideoFile(Long videoFileId, Principal principal) {
        try {
            User user = utilsService.getUserByPrincipal(principal);
            VideoFile videoFile = videoService.getVideoFileById(videoFileId);
            if (isUserLikeVideoFile(user, videoFile)) {
                UserLike userLike = getUserLikeByUserAndVideoFile(user, videoFile);
                if (userLike != null) {
                    videoFile.setLikes(videoFile.getLikes() - 1);
                    videoFileRepository.save(videoFile);
                    userLikeRepository.delete(userLike);
                    LOG.info("Delete Like for User: {}. And videoFile: {}", user.getId(), videoFile.getId());
                    return true;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public  List<UserLike> getUserLikesFromPost(Long postId) {
        Post post = postService.getPostById(postId);
        return userLikeRepository.findAllByPost(post);
    }

    public  List<UserLike> getUserLikesFromComment(Long commentId) {
        Comment comment = commentService.getCommentById(commentId);
        return userLikeRepository.findAllByComment(comment);
    }

    public List<UserLike> getUserLikesFromCommonImage(Long commonImageId) {
        CommonImage commonImage = commonImageService.getCommonImageById(commonImageId);
        return userLikeRepository.findAllByCommonImage(commonImage);
    }

    public  List<UserLike> getUserLikesFromAudioFile(Long audioFileId) {
        AudioFile audioFile = audioService.getAudioFileById(audioFileId);
        return userLikeRepository.findAllByAudioFile(audioFile);
    }

    public List<UserLike> getUserLikesFromVideoFile(Long videoFileId) {
        VideoFile videoFile = videoService.getVideoFileById(videoFileId);
        return userLikeRepository.findAllByVideoFile(videoFile);
    }

    public UserLike getUserLikeByUserAndPost(User user, Post post) {
        return userLikeRepository.findByUserAndPost(user, post)
                .orElse(null);
    }
    public UserLike getUserLikeByUserAndComment(User user, Comment comment) {
        return userLikeRepository.findByUserAndComment(user, comment)
                .orElse(null);
    }
    public UserLike getUserLikeByUserAndCommonImage(User user, CommonImage commonImage) {
        return userLikeRepository.findByUserAndCommonImage(user, commonImage)
                .orElse(null);
    }

    private UserLike getUserLikeByUserAndAudioFile(User user, AudioFile audioFile) {
        return userLikeRepository.findByUserAndAudioFile(user, audioFile)
                .orElse(null);
    }

    private UserLike getUserLikeByUserAndVideoFile(User user, VideoFile videoFile) {
        return userLikeRepository.findByUserAndVideoFile(user, videoFile)
                .orElse(null);
    }

    public boolean isUserLikePost(User user, Post post) {
        UserLike userLike = getUserLikeByUserAndPost(user, post);
        return userLike != null;
    }

    public boolean isUserLikeComment(User user, Comment comment) {
        UserLike userLike = getUserLikeByUserAndComment(user, comment);
        return userLike != null;
    }

    public boolean isUserLikeCommonImage(User user, CommonImage commonImage) {
        UserLike userLike = getUserLikeByUserAndCommonImage(user, commonImage);
        return userLike != null;
    }

    public boolean isUserLikeAudioFile(User user, AudioFile audioFile) {
        UserLike userLike = getUserLikeByUserAndAudioFile(user, audioFile);
        return userLike != null;
    }

    public boolean isUserLikeVideoFile(User user, VideoFile videoFile) {
        UserLike userLike = getUserLikeByUserAndVideoFile(user, videoFile);
        return userLike != null;
    }
}
