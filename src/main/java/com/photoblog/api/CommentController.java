package com.photoblog.api;

import com.photoblog.api.request.CommentRequest;
import com.photoblog.api.request.PostRequest;
import com.photoblog.api.response.CommentResponse;
import com.photoblog.api.response.PostResponse;
import com.photoblog.service.CommentService;
import com.photoblog.service.PostService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public void save(@Valid @RequestBody CommentRequest commentRequest,@Parameter(hidden = true) @AuthenticationPrincipal UserDetails user){
        commentService.save(commentRequest, user.getUsername());
    }

    @GetMapping("/{id}")
    public CommentResponse findById(@PathVariable Integer id){
        return commentService.findById(id);
    }

    @GetMapping("/post/{id}")
    public List<CommentResponse> findByPost(@PathVariable Integer id){

        return commentService.findByPost(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @Valid @RequestBody CommentRequest commentRequest,@Parameter(hidden = true) @AuthenticationPrincipal UserDetails user){
        commentService.update(id, commentRequest, user.getUsername());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id,@Parameter(hidden = true) @AuthenticationPrincipal UserDetails user){
        commentService.delete(id, user.getUsername());
    }
}
