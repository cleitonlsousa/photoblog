package com.photoblog.api.response;

public class ImageResponse {

    private Integer id;
    private String fileName;
    private String contentType;
    private byte[] img;

    public ImageResponse(Integer id, String fileName, String contentType, byte[] img) {
        this.id = id;
        this.fileName = fileName;
        this.contentType = contentType;
        this.img = img;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
