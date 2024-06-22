package org.example.blackshoe.esthetereportservice.repository;

import org.example.blackshoe.esthetereportservice.dto.PhotoDto;
import org.example.blackshoe.esthetereportservice.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long>{
    /*
            this.writerId = writerId;
            this.nickname = nickname == null ? "" : nickname;
            this.profileCloudfrontUrl = profileCloudfrontUrl == null ? "" : profileCloudfrontUrl;
            this.photoId = photoId == null ? "" : photoId;
            this.photoCloudfrontUrl = photoCloudfrontUrl == null ? "" : photoCloudfrontUrl;
            this.exhibitionTitle = exhibitionTitle == null ? "" : exhibitionTitle;
            this.description = description == null ? "" : description;
     */
    @Query("SELECT new org.example.blackshoe.esthetereportservice.dto.PhotoDto$ReadBasicInfoResponse" +
            "(r.writerId, u.nickname, u.profileCloudfrontUrl, r.photo.photoId, r.photo.photoCloudfrontUrl, r.photo.exhibitionTitle, r.description) " +
            "FROM Report r " +
            "LEFT JOIN User u ON r.writerId = u.userId " +
            "ORDER BY r.reportedAt DESC")
    Page<PhotoDto.ReadBasicInfoResponse> readPhotos(Pageable pageable);
}
