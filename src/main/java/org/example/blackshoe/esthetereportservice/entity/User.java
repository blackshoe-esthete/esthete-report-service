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

    @Column(name = "profile_img_url", nullable = false, columnDefinition = "VARCHAR(250) default 'default'")
    private String profileImgUrl;

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

    public void updateProfileImgUrl(String profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
    }

    @Builder
    public User(String nickname, String profileImgUrl) {
        this.nickname = nickname;
        this.profileImgUrl = profileImgUrl;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }
}
