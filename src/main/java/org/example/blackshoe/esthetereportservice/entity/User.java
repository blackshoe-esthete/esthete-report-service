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
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_uuid", columnDefinition = "BINARY(16)", unique = true, nullable = false)
    private UUID userId;

    @Column(name = "nickname", nullable = false, length = 50)
    private String nickname;

    @Column(name = "profile_cloudfront_url", nullable = false, length = 50)
    private String profileCloudfrontUrl;

    @Column(name = "report_received_count", nullable = false, columnDefinition = "BIGINT default 0")
    private Long reportReceivedCount;

    @Column(name = "report_made_count", nullable = false, columnDefinition = "BIGINT default 0")
    private Long reportMadeCount;

    public void increaseReportReceivedCount() {
        this.reportReceivedCount++;
    }

    public void increaseReportMadeCount() {
        this.reportMadeCount++;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Builder
    public User(String nickname, String profileCloudfrontUrl) {
        this.nickname = nickname;
        this.profileCloudfrontUrl = profileCloudfrontUrl;
    }
}
