package com.photoblog.api;

import com.photoblog.api.response.ImageResponse;
import com.photoblog.service.ImageService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/{albumId}")
    public void save(@PathVariable Integer albumId, @RequestParam("file") MultipartFile file,
                     @Parameter(hidden = true) @AuthenticationPrincipal User user){

        imageService.save(albumId, file, user.getUsername());
    }

    @GetMapping("/album/{albumId}")
    public List<ImageResponse> findByAlbum(@PathVariable Integer albumId){
        return imageService.findByAlbum(albumId);
    }

}
