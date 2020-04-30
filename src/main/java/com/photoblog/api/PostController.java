package com.photoblog.api;

import com.photoblog.api.request.PostRequest;
import com.photoblog.service.PostService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public void save(@RequestBody PostRequest postRequest, @AuthenticationPrincipal User user){
        postService.save(postRequest, user.getUsername());
    }
}
