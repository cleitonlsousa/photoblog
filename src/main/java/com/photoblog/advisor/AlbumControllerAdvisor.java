package com.photoblog.advisor;

import com.photoblog.exception.AlbumNotFoundException;
import com.photoblog.exception.CommentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class AlbumControllerAdvisor extends BaseControllerAdvisor {

    @ExceptionHandler(AlbumNotFoundException.class)
    public ResponseEntity<Object> albumNotFoundException(AlbumNotFoundException ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }


}
