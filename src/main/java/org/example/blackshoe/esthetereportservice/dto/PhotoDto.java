package org.example.blackshoe.esthetereportservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

public class PhotoDto {
    @Data
    @NoArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ReadBasicInfoResponse {
        private UUID writerId;
        private String nickname;
        private String profileImgUrl;
        private UUID photoId;
        private String photoImgUrl;
        private String exhibitionTitle;
        private String description;
        @Builder
        public ReadBasicInfoResponse(UUID writerId, String nickname, String profileImgUrl, UUID photoId, String photoImgUrl, String exhibitionTitle, String description) {
            this.writerId = writerId;
            this.nickname = nickname == null ? "" : nickname;
            this.profileImgUrl = profileImgUrl == null ? "" : profileImgUrl;
            this.photoId = photoId;
            this.photoImgUrl = photoImgUrl == null ? "" : photoImgUrl;
            this.exhibitionTitle = exhibitionTitle == null ? "" : exhibitionTitle;
            this.description = description == null ? "" : description;
        }
    }

    @Data
    @NoArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class GetDetailInfoResponse {
        private String exhibitionTitle;
        private UUID photoId;
        private LocalDateTime createdAt;
        private LocalDateTime reportedAt;
        private String description;
        private UUID writerId;
        private String photoImgUrl;
        private String nickname;
        private String profileImgUrl;

        private Long photoReportReceivedCount;
        private Long userReportReceivedCount;

        @Builder
        public GetDetailInfoResponse(
                String exhibitionTitle,
                UUID photoId,
                LocalDateTime createdAt,
                LocalDateTime reportedAt,
                String description,
                UUID writerId,
                String photoImgUrl,
                String nickname,
                String profileImgUrl,
                Long photoReportReceivedCount,
                Long userReportReceivedCount
        ) {
            this.exhibitionTitle = exhibitionTitle == null ? "" : exhibitionTitle;
            this.photoId = photoId;
            this.createdAt = createdAt;
            this.reportedAt = reportedAt;
            this.description = description == null ? "" : description;
            this.writerId = writerId;
            this.photoImgUrl = photoImgUrl == null ? "" : photoImgUrl;
            this.nickname = nickname == null ? "" : nickname;
            this.profileImgUrl = profileImgUrl == null ? "" : profileImgUrl;

            this.photoReportReceivedCount = photoReportReceivedCount == null ? 0 : photoReportReceivedCount;
            this.userReportReceivedCount = userReportReceivedCount == null ? 0 : userReportReceivedCount;
        }
    }
}
