package com.photoblog.service;

import com.photoblog.api.request.AlbumRequest;
import com.photoblog.api.request.CommentRequest;
import com.photoblog.api.response.AlbumResponse;
import com.photoblog.api.response.CommentResponse;
import com.photoblog.exception.AlbumNotFoundException;
import com.photoblog.exception.CommentNotFoundException;
import com.photoblog.exception.UnauthorizedException;
import com.photoblog.model.Album;
import com.photoblog.model.Comment;
import com.photoblog.respository.AlbumRepository;
import com.photoblog.respository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final AccountService accountService;

    @Autowired
    public AlbumService(AlbumRepository albumRepository, AccountService accountService) {
        this.albumRepository = albumRepository;
        this.accountService = accountService;
    }

    public void save(AlbumRequest albumRequest, String userName){

        albumRepository.save(new Album(
                albumRequest.getDescription(),
                LocalDate.now(),
                accountService.findByEmail(userName)
        ));
    }

    public List<AlbumResponse> findAll(){
        return albumRepository.findAll().stream().map(this::getAlbumResponse).collect(Collectors.toList());
    }

    public AlbumResponse findById(Integer id) {
        return getAlbumResponse(getAlbum(id));
    }

    public List<AlbumResponse> findByCreated(String email){
        return albumRepository.findByCreatedBy_Email(email).stream().map(this::getAlbumResponse).collect(Collectors.toList());
    }

    public void update(Integer id, AlbumRequest albumRequest, String userName) {
        Album album = getAlbum(id);

        validateCreatedBY(userName, album);

        album.setDescription(albumRequest.getDescription());

        albumRepository.save(album);

    }

    public void delete(Integer id, String userName) {
        Album album = getAlbum(id);

        validateCreatedBY(userName, album);

        albumRepository.delete(album);

    }

    private AlbumResponse getAlbumResponse(Album album) {
        return new AlbumResponse(
                album.getId(),
                album.getDescription(),
                album.getCreatedBy().getFullName(),
                album.getCreatedDate());
    }

    private void validateCreatedBY(String userName, Album album) {
        if(!album.getCreatedBy().getEmail().equals(userName))
            throw new UnauthorizedException();
    }

    protected Album getAlbum(Integer id) {
        return albumRepository.findById(id).orElseThrow(() -> new AlbumNotFoundException(id));
    }

}
