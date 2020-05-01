package com.photoblog.respository;

import com.photoblog.model.Album;
import com.photoblog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album,Integer> {

        List<Album> findByCreatedBy_Email(String email);

}
