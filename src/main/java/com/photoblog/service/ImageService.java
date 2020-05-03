package com.photoblog.service;

import com.photoblog.api.response.ImageResponse;
import com.photoblog.exception.ImageNotFoundException;
import com.photoblog.exception.ImageSaveException;
import com.photoblog.exception.UnauthorizedException;
import com.photoblog.model.Album;
import com.photoblog.model.Image;
import com.photoblog.respository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Component
public class ImageService {

    private final ImageRepository imageRepository;
    private final AlbumService albumService;

    @Autowired
    public ImageService(ImageRepository imageRepository, AlbumService albumService) {
        this.imageRepository = imageRepository;
        this.albumService = albumService;
    }

    public void save(Integer albumId, MultipartFile file, String email) {

        Album album = albumService.getAlbum(albumId);

        validateAuthorization(album, email);

        try {

            imageRepository.save(
                    new Image(
                            album,
                            file.getOriginalFilename(),
                            file.getContentType(),
                            compressBytes(file.getBytes())));

        } catch (IOException ex) {
            throw new ImageSaveException();
        }

    }

    public List<ImageResponse> findByAlbum(Integer albumId) {

        return imageRepository.findByAlbum_Id(albumId)
                .orElseThrow(() -> new ImageNotFoundException(albumId))
                .stream().map(this::getImageResponse).collect(Collectors.toList());

    }

    protected void deleteByAlbum(Integer album) {
        imageRepository.deleteByAlbum_Id(album);
    }

    private ImageResponse getImageResponse(Image image) {
        return new ImageResponse(
                image.getId(), image.getFileName(), image.getContentType(), decompressBytes(image.getImg()));
    }

    private void validateAuthorization(Album album, String email) {

        if(!album.getCreatedBy().getEmail().equals(email))
            throw new UnauthorizedException();
    }


    private byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            throw new ImageSaveException();
        }
        return outputStream.toByteArray();
    }

    private byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (Exception ioe) {
            throw new ImageSaveException();
        }
        return outputStream.toByteArray();
    }
}