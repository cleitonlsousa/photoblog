package com.photoblog.service;

import com.photoblog.exception.StorageException;
import com.photoblog.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Component
public class PhotoService {


    private AccountService accountService;

    @Autowired
    public PhotoService(AccountService accountService) {
        this.accountService = accountService;
    }

    public void save(String album, MultipartFile file, String email) {

        Account account = accountService.findByEmail(email);

        String path = getPath(account.getPathId(), album);

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try{

            if(fileName.contains("..")) {
                System.out.println("Sorry! Filename contains invalid path sequence " + fileName);
            }

            File dir = new File(path);
            if(!dir.exists()){
                dir.mkdirs();
            }

            //Path targetLocation = Paths.get(path + fileName);
            //Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            Path targetLocation = Paths.get(path + file.getOriginalFilename());
            Files.write(targetLocation, file.getBytes());

        } catch (IOException ex) {
            throw new StorageException(fileName);
        }
    }

    private String getPath(String pathId, String album){
        return new StringBuilder()
                .append(System.getProperty("user.home"))
                .append(File.separator)
                .append("photoblog")
                .append(File.separator)
                .append("photo")
                .append(File.separator)
                .append(pathId)
                .append(File.separator)
                .append(album)
                .append(File.separator)
                .toString();
    }
}