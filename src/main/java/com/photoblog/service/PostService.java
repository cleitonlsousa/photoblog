package com.photoblog.service;

import com.photoblog.api.request.PostRequest;
import com.photoblog.api.response.PostResponse;
import com.photoblog.model.Account;
import com.photoblog.model.Post;
import com.photoblog.respository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

        postRepository.save(new Post(
                postRequest.getTitle(),
                postRequest.getBody(),
                accountService.getByEmail(userName),
                LocalDateTime.now()
        ));
    }

    public List<PostResponse> getAll(){
        return postRepository.findAll().stream().map(this::getPostResponse).collect(Collectors.toList());
    }

    private PostResponse getPostResponse(Post p) {

        return new PostResponse(
                p.getId(),
                p.getTitle(),
                p.getBody(),
                p.getCreatedBy().getFullName(),
                p.getCreatedDate());
    }

}
