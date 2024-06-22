package org.example.blackshoe.esthetereportservice.repository;

import org.example.blackshoe.esthetereportservice.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    @Modifying
    @Query("DELETE FROM Photo p WHERE p.photoId = :photoUUID")
    void deleteByPhotoId(UUID photoUUID);
}
