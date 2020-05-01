package com.photoblog.respository;

import com.photoblog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

        List<Comment> findByPost(Integer post);
        Optional<Comment> findByIdAndCreatedBy_Email(Integer id, String email);
}
