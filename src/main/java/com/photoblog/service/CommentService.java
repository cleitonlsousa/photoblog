package com.photoblog.service;

import com.photoblog.api.request.CommentRequest;
import com.photoblog.api.response.CommentResponse;
import com.photoblog.exception.CommentNotFoundException;
import com.photoblog.exception.UnauthorizedException;
import com.photoblog.model.Comment;
import com.photoblog.respository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentService {

    private final CommentRepository commentRepository;
    private final AccountService accountService;

    @Autowired
    public CommentService(CommentRepository commentRepository, AccountService accountService) {
        this.commentRepository = commentRepository;
        this.accountService = accountService;
    }

    public void save(CommentRequest commentRequest, String userName){

        commentRepository.save(new Comment(
                commentRequest.getComment(),
                LocalDate.now(),
                accountService.findByEmail(userName),
                commentRequest.getPostId()
        ));
    }

    public CommentResponse findById(Integer id) {
        return getCommentResponse(getComment(id));
    }

    public List<CommentResponse> findByPost(Integer postId){
        return commentRepository.findByPost(postId).stream().map(this::getCommentResponse).collect(Collectors.toList());
    }

    public void update(Integer id, CommentRequest commentRequest, String userName) {
        Comment comment = getComment(id);

        validateCreatedBY(userName, comment);

        comment.setBody(commentRequest.getComment());

        commentRepository.save(comment);

    }

    public void delete(Integer id, String userName) {
        Comment comment = getComment(id);

        validateCreatedBY(userName, comment);

        commentRepository.delete(comment);

    }

    protected void deleteByPost(Integer post) {
        commentRepository.deleteByPost(post);
    }

    private CommentResponse getCommentResponse(Comment c) {
        return new CommentResponse(
                c.getId(),
                c.getCreatedBy().getFullName(),
                c.getBody(),
                c.getCreatedDate());
    }

    private void validateCreatedBY(String userName, Comment comment) {
        if(!comment.getCreatedBy().getEmail().equals(userName))
            throw new UnauthorizedException();
    }

    private Comment getComment(Integer id) {
        return commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
    }

}
