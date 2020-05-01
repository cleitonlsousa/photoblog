package com.photoblog.api;

import com.photoblog.api.request.AlbumRequest;
import com.photoblog.api.request.CommentRequest;
import com.photoblog.api.response.AlbumResponse;
import com.photoblog.api.response.CommentResponse;
import com.photoblog.service.AlbumService;
import com.photoblog.service.CommentService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/album")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping
    public void save(@Valid @RequestBody AlbumRequest albumRequest,@Parameter(hidden = true) @AuthenticationPrincipal User user){
        albumService.save(albumRequest, user.getUsername());
    }

    @GetMapping
    public List<AlbumResponse> findAll(){
        return albumService.findAll();
    }

    @GetMapping("/{id}")
    public AlbumResponse findById(@PathVariable Integer id){
        return albumService.findById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @Valid @RequestBody AlbumRequest albumRequest,@Parameter(hidden = true) @AuthenticationPrincipal User user){
        albumService.update(id, albumRequest, user.getUsername());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id,@Parameter(hidden = true) @AuthenticationPrincipal User user){
        albumService.delete(id, user.getUsername());
    }
}
