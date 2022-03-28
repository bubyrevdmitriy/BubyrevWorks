package com.example.demo.service;

import com.example.demo.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FeedService {

    public static final Logger LOG = LoggerFactory.getLogger(FeedService.class);

    private final PostService postService;
    private final UtilsService utilsService;
    private final UserFriendService userFriendService;
    private final UserGroupInviteService userGroupInviteService;

    @Autowired
    public FeedService(PostService postService,
                       UtilsService utilsService,
                       UserFriendService userFriendService,
                       UserGroupInviteService userGroupInviteService
    ) {
        this.postService = postService;
        this.utilsService = utilsService;
        this.userFriendService = userFriendService;
        this.userGroupInviteService = userGroupInviteService;
    }

    public List<Post> getAllFeedForUserPagination(Principal principal, Pageable pageable) {
        Post[] resultPostsArray = getAllFeedForUser(principal);
        Post[] resultPostsArrayPagination = new Post[pageable.getPageSize()];//make array////////////////////////////////////////////////////////////
        for (int i = 0; i < resultPostsArrayPagination.length; i++) {
            if(pageable.getPageNumber()*pageable.getPageSize()+i < resultPostsArray.length) {
                resultPostsArrayPagination[i] = resultPostsArray[pageable.getPageNumber()*pageable.getPageSize()+i];
            }

        }
        return Arrays.asList(resultPostsArrayPagination);
    }

    public Post[] getAllFeedForUser(Principal principal) {
        User user = utilsService.getUserByPrincipal(principal);
        List<Post> resultPosts = new ArrayList<>();
        List<UserFriendPair> userFriendPairs = userFriendService.getUserFriendPairsFromUser(user.getId());
        List<UserFriendInvite> userFriendInvites = userFriendService.getUserFriendInvitesSendFromUser(user.getId());
        List<UserGroupPair> userGroupPairs = userGroupInviteService.getUserGroupPairsFromUser(user.getId());//getUserGroupPairsFromUser

        for (UserFriendPair userFriendPair : userFriendPairs) {
            User otherUser;
            if (userFriendPair.getRecipientUser().equals(user)) {
                otherUser = userFriendPair.getSenderUser();
            } else {
                otherUser = userFriendPair.getRecipientUser();
            }
            List<Post> userFriendPosts = postService.getAllPostsFromUser(otherUser.getId());
            resultPosts.addAll(userFriendPosts);
        }

        for (UserFriendInvite userFriendInvite : userFriendInvites) {
            User otherUser = userFriendInvite.getRecipientUser();
            List<Post> userFriendInvitePosts = postService.getAllPostsFromUser(otherUser.getId());
            resultPosts.addAll(userFriendInvitePosts);
        }

        for (UserGroupPair userGroupPair : userGroupPairs) {
            List<Post> userGroupPosts = postService.getAllPostsFromUserGroup(userGroupPair.getUserGroup().getId());
            resultPosts.addAll(userGroupPosts);
        }
        resultPosts.addAll(postService.getAllPostsFromUser(user.getId()));

        Post[] resultPostsArray = new Post[resultPosts.size()];//make array
        resultPosts.toArray(resultPostsArray); // fill the array

        mergeSortIntegers(resultPostsArray, resultPostsArray.length);

        return resultPostsArray;
    }

    public static void mergeSortIntegers(Post[] a, int n) {
        // проверяем не 1 ли элемент в массиве?
        if (n < 2) {
            return;
        }

        int mid = n / 2;// находим средний элемент
        Post[] l = new Post[mid];// создаем массив для левой части переданного в метод массива
        Post[] r = new Post[n - mid];// создаем массив для правой части переданного в метод массива

        // копируем левую часть от начала до середины
        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        // копируем правую часть от середины до конца массива
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSortIntegers(l, mid); // рекурсивно запускаем метод с левым массивом
        mergeSortIntegers(r, n - mid); // рекурсивно запускаем метод с правым массивом

        merge(a, l, r, mid, n - mid);
    }

    public static void merge( Post[] a, Post[] l, Post[] r, int left, int right) {
        // в массив a мы вписываем элементы из массивов l и r, left и right длины массивов l и r
        int i = 0, j = 0, k = 0;// i-индекс в массиве-источнике l, j-индекс в массиве-источнике r, k-индекс в массиве-записе a
        while (i < left && j < right) { //пока остались непрочтенные строки в массиве-источнике l и r
            if (l[i].getCreatedDate().isBefore(r[j].getCreatedDate())) { //сортировка по убыванию
                a[k++] = r[j++];
            }
            else {
                a[k++] = l[i++];
            }
        }
        while (i < left) { // пока остались непрочтенные строки в массиве-источнике l
            a[k++] = l[i++];
        }
        while (j < right) { //пока остались непрочтенные строки в массиве-источнике r
            a[k++] = r[j++];
        }
    }

    public Long countAllFeedForUser(Principal principal) {
        Post[] resultPostsArray = getAllFeedForUser(principal);
        return (long) resultPostsArray.length;
    }
}
