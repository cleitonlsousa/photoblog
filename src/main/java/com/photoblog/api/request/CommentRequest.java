package com.photoblog.api.request;

import javax.validation.constraints.NotNull;

public class CommentRequest {

    @NotNull(message = "postId is required")
    private Integer postId;
    @NotNull(message = "comment is required")
    private String comment;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
