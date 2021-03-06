package com.photoblog.respository;

import com.photoblog.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image,Integer> {

        Optional<List<Image>> findByAlbum_Id(Integer album);

        void deleteByAlbum_Id(Integer album);


}
