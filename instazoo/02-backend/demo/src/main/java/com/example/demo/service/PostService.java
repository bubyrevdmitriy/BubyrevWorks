package com.example.demo.service;

import com.example.demo.dto.PostDTO;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.exception.PostNotFoundException;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
public class PostService {
    public static final Logger LOG = LoggerFactory.getLogger(PostService.class);

    private final PostRepository postRepository;
    private final UtilsService utilsService;
    private final ImageRepository imageRepository;

    @Autowired
    public PostService(PostRepository postRepository,
                       UtilsService utilsService,
                       ImageRepository imageRepository
    ) {
        this.postRepository = postRepository;
        this.utilsService = utilsService;
        this.imageRepository = imageRepository;
    }

    public Post createPost(PostDTO postDTO, User user) {
        Post post = new Post();
        post.setUser(user);
        post.setCaption(postDTO.getCaption());
        post.setLocation(postDTO.getLocation());
        post.setTitle(postDTO.getTitle());
        post.setLikes(0);

        LOG.info("Saving Post for User: {}", user.getEmail());
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByCreatedDateDesc();
    }

    public Post getPostByIdAndPrincipal (Long postId, Principal principal) {
        User user = utilsService.getUserByPrincipal(principal);
        return postRepository.findPostByIdAndUser(postId, user)
                .orElseThrow(() -> new PostNotFoundException("Post cannot be found for user: " + user.getEmail()));
    }

    public Post getPostById (Long postId) {
        return postRepository.findPostById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post cannot be found post with id: " + postId));
    }

    public  List<Post> getAllPostsFromUser(User user) {
        return postRepository.findAllByUserOrderByCreatedDateDesc(user);
    }

    @Transactional
    public void deletePost(Long postId, Principal principal) {
        Post post = getPostByIdAndPrincipal(postId, principal);
        //Optional<ImageModel> imageModel = imageRepository.findByPost(post);
        postRepository.delete(post);
        //imageModel.ifPresent(imageRepository::delete);
    }
}
