package com.photoblog;

import com.photoblog.api.request.AlbumRequest;
import com.photoblog.exception.AccountNFException;
import com.photoblog.exception.UnauthorizedException;
import com.photoblog.model.Account;
import com.photoblog.model.Album;
import com.photoblog.respository.AlbumRepository;
import com.photoblog.respository.ImageRepository;
import com.photoblog.service.AccountService;
import com.photoblog.service.AlbumService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class AlbumServiceTest {

    private AlbumService albumService;
    private final String email = "email@email.com";
    private AccountService accountService;
    private AlbumRepository albumRepository;

    @Before
    public void before(){

        accountService = mock(AccountService.class);
        albumRepository = mock(AlbumRepository.class);
        ImageRepository imageRepository = mock(ImageRepository.class);
        albumService = new AlbumService(albumRepository, accountService, imageRepository);

    }

    @Test
    public void save(){
        AlbumRequest request = new AlbumRequest();
        request.setDescription("Ferias");

        albumService.save(request, email);

    }

    @Test(expected = AccountNFException.class)
    public void saveAccountNotFound(){

        when(accountService.findByEmail(anyString())).thenThrow(new AccountNFException(email));

        AlbumRequest request = new AlbumRequest();
        request.setDescription("Ferias");

        albumService.save(request, email);

    }

    @Test
    public void update(){
        Integer albumId = 1;

        AlbumRequest request = new AlbumRequest();
        request.setDescription("Acampamento");

        Account account = new Account();
        account.setEmail(email);

        Album album = new Album();
        album.setCreatedBy(account);

        when(albumRepository.findById(anyInt())).thenReturn(Optional.of(album));

        albumService.update(albumId, request, email);
    }

    @Test(expected = UnauthorizedException.class)
    public void updateUnauthorized(){
        Integer albumId = 1;

        AlbumRequest request = new AlbumRequest();
        request.setDescription("Acampamento");

        Account account = new Account();
        account.setEmail("email2@email.com");

        Album album = new Album();
        album.setCreatedBy(account);

        when(albumRepository.findById(anyInt())).thenReturn(Optional.of(album));

        albumService.update(albumId, request, email);
    }

    @Test
    public void delete(){
        Integer albumId = 1;

        Account account = new Account();
        account.setEmail("email@email.com");

        Album album = new Album();
        album.setCreatedBy(account);

        when(albumRepository.findById(anyInt())).thenReturn(Optional.of(album));

        albumService.delete(albumId, email);
    }

    @Test(expected = UnauthorizedException.class)
    public void deleteUnauthorized(){
        Integer albumId = 1;

        Account account = new Account();
        account.setEmail("email2@email.com");

        Album album = new Album();
        album.setCreatedBy(account);

        when(albumRepository.findById(anyInt())).thenReturn(Optional.of(album));

        albumService.delete(albumId, email);
    }

}
