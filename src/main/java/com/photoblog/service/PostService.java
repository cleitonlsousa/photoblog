package com.photoblog.service;

import com.photoblog.api.request.PostRequest;
import com.photoblog.model.Account;
import com.photoblog.model.Post;
import com.photoblog.respository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PostService {

    private PostRepository postRepository;
    private AccountService accountService;

    @Autowired
    public PostService(PostRepository postRepository, AccountService accountService) {
        this.postRepository = postRepository;
        this.accountService = accountService;
    }

    public void save(PostRequest postRequest, String userName){

        Account account = accountService.getByEmail(userName);

        postRepository.save(new Post(
                postRequest.getTitle(),
                postRequest.getBody(),
                account.getId(),
                LocalDateTime.now()
        ));
    }

}
