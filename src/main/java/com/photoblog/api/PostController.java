package com.photoblog.api;

import com.photoblog.api.request.PostRequest;
import com.photoblog.api.response.PostResponse;
import com.photoblog.service.PostService;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public void save(@Valid @RequestBody PostRequest postRequest, @AuthenticationPrincipal User user){
        postService.save(postRequest, user.getUsername());
    }

    @GetMapping
    public List<PostResponse> getAll(){
        return postService.getAll();
    }

    @GetMapping("/{postId}")
    public PostResponse findById(@PathVariable Integer postId){
        return postService.findById(postId);
    }

    @GetMapping("/find")
    public List<PostResponse> findBy(@RequestParam Map<String, String> params){
        return postService.findBy(params);
    }

    @PutMapping("/{postId}")
    public void update(@PathVariable Integer postId, @Valid @RequestBody PostRequest postRequest, @AuthenticationPrincipal User user){
        postService.update(postId, postRequest, user.getUsername());
    }

    @DeleteMapping("/{postId}")
    public void delete(@PathVariable Integer postId, @AuthenticationPrincipal User user){
        postService.delete(postId, user.getUsername());
    }
}
