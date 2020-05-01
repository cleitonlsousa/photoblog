package com.photoblog.api;

import com.photoblog.api.request.PostRequest;
import com.photoblog.api.response.PostResponse;
import com.photoblog.service.PostService;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping
    public List<PostResponse> getAll(){
        return postService.getAll();
    }

    @GetMapping("/find")
    public List<PostResponse> findBy(@RequestParam Map<String, String> params){
        return postService.findBy(params);
    }

    @PutMapping("/{postId}")
    public void update(@PathVariable Integer postId, @RequestBody PostRequest postRequest, @AuthenticationPrincipal User user){
        postService.update(postId, postRequest, user.getUsername());
    }
}
