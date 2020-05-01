package com.photoblog.api;

import com.photoblog.service.PhotoService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/photo")
public class PhotoController {

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping
    public void save(@RequestParam("album") String album, @RequestParam("file") MultipartFile file,
                     @AuthenticationPrincipal User user){

        photoService.save(album, file, user.getUsername());
    }

}
