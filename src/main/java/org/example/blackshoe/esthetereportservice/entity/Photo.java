package org.example.blackshoe.esthetereportservice.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.blackshoe.esthetereportservice.common.BaseEntity;

import java.util.UUID;

@Entity
@Getter
@Table(name = "photos")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Photo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private Long id;

    @Column(name = "photo_uuid", columnDefinition = "BINARY(16)", unique = true, nullable = false)
    private UUID photoId;

    @Column(name = "exhibition_title", nullable = false, length = 50)
    private String exhibitionTitle;

    @Column(name = "photo_img_url", nullable = false, columnDefinition = "VARCHAR(250) default 'default'")
    private String photoImgUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id", foreignKey = @ForeignKey(name = "photos_fk_report_id"))
    private Report report;

    @Builder
    public Photo(String photoImgUrl,
                 String exhibitionTitle) {
        this.photoImgUrl = photoImgUrl;
        this.exhibitionTitle = exhibitionTitle;
    }

    public void updateReport(Report report) {
        this.report = report;
        report.setPhoto(this);
    }

    public void setPhotoId(UUID photoId) {
        this.photoId = photoId;
    }

}

