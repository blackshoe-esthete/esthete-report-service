package org.example.blackshoe.esthetereportservice.repository;

import org.example.blackshoe.esthetereportservice.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Modifying
    @Query("DELETE FROM Comment c WHERE c.commentId = :commentUUID")
    void deleteByCommentId(UUID commentUUID);

    Optional<Comment> findByCommentId(UUID commentUUID);
}
