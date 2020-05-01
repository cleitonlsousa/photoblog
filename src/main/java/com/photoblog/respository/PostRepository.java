package com.photoblog.respository;

import com.photoblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

        List<Post> findByCreatedBy_Id(Integer createdBY);
        List<Post> findByCreatedBy_IdAndCreatedDateGreaterThanEqual(Integer createdBY, LocalDate createdDate);
        List<Post> findByCreatedBy_IdAndCreatedDateBetween(Integer createdBY, LocalDate initialDate, LocalDate finalDate);
        List<Post> findByCreatedDateBetween(LocalDate initialDate, LocalDate finalDate);
        List<Post> findByCreatedDateGreaterThanEqual(LocalDate initialDate);
}
