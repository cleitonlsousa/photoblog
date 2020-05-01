package com.photoblog.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "album")
    private Album album;

    @Column(name="file_name")
    private String fileName;

    @Column(name = "content_type")
    private String contentType;

    private byte[] img;

    public Image() {}

    public Image(Album album, String fileName, String contentType, byte[] img) {
        this.album = album;
        this.fileName = fileName;
        this.contentType = contentType;
        this.img = img;
    }

    public Integer getId() {
        return id;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}
