package org.example.blackshoe.esthetereportservice.repository;

import org.example.blackshoe.esthetereportservice.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
