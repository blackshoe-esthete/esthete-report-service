package org.example.blackshoe.esthetereportservice.repository;

import org.example.blackshoe.esthetereportservice.dto.CommentDto;
import org.example.blackshoe.esthetereportservice.dto.PhotoDto;
import org.example.blackshoe.esthetereportservice.entity.Comment;
import org.example.blackshoe.esthetereportservice.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long>{

    @Query("SELECT new org.example.blackshoe.esthetereportservice.dto.PhotoDto$ReadBasicInfoResponse" +
            "(r.writerId, u.nickname, u.profileCloudfrontUrl, r.photo.photoId, r.photo.photoCloudfrontUrl, r.photo.exhibitionTitle, r.description) " +
            "FROM Report r " +
            "LEFT JOIN User u ON r.writerId = u.userId " +
            "ORDER BY r.reportedAt DESC")
    Page<PhotoDto.ReadBasicInfoResponse> readPhotos(Pageable pageable);

/*
        @Builder
        public GetDetailInfoResponse(
                String exhibitionTitle,
                UUID photoId,
                LocalDateTime reportedAt,
                String description,
                String writerId,
                String nickname,
                String profileCloudfrontUrl,
                Long photoReportReceivedCount,
                Long userReportReceivedCount
        ) {
 */
    @Query("SELECT new org.example.blackshoe.esthetereportservice.dto.PhotoDto$GetDetailInfoResponse" +
            "(r.photo.exhibitionTitle, r.photo.photoId, r.photo.createdAt, r.reportedAt, r.description, r.writerId, u.nickname, u.profileCloudfrontUrl, " +
            "(SELECT COUNT(pr) FROM Report pr WHERE pr.photo.photoId = :photoUUID), " +
            "u.reportReceivedCount" +
            ") " +
            "FROM Report r " +
            "LEFT JOIN User u ON r.writerId = u.userId " +
            "WHERE r.photo.photoId = :photoUUID")
    PhotoDto.GetDetailInfoResponse getPhotoDetailByPhotoId(UUID photoUUID);

    @Query("SELECT new org.example.blackshoe.esthetereportservice.dto.CommentDto$ReadBasicInfo" +
            "(r.writerId, u.nickname, u.profileCloudfrontUrl, r.description) " +
            "FROM Report r " +
            "LEFT JOIN User u ON r.writerId = u.userId " +
            "ORDER BY r.reportedAt ASC")
    Page<CommentDto.ReadBasicInfo> readComments(Pageable pageable);

    @Query("SELECT new org.example.blackshoe.esthetereportservice.dto.CommentDto$GetDetailInfo" +
            "(r.comment.commentId, r.comment.createdAt, r.reportedAt, r.description, u.profileCloudfrontUrl, u.nickname, r.writerId, u.reportReceivedCount) " +
            "FROM Report r " +
            "LEFT JOIN User u ON r.writerId = u.userId " +
            "WHERE r.comment.commentId = :commentUUID " +
            "ORDER BY r.reportedAt ASC")
    CommentDto.GetDetailInfo getCommentDetailByCommentId(UUID commentUUID);

    @Modifying
    @Query("DELETE FROM Report r WHERE r.photo.photoId = :photoUUID")
    void deleteByPhotoId(UUID photoUUID);

    @Modifying
    @Query("DELETE FROM Report r WHERE r.comment.commentId = :commentUUID")
    void deleteByCommentId(UUID commentUUID);
}
