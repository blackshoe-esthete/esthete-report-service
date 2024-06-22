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
        private String profileCloudfrontUrl;
        private UUID photoId;
        private String photoCloudfrontUrl;
        private String exhibitionTitle;
        private String description;
        @Builder
        public ReadBasicInfoResponse(UUID writerId, String nickname, String profileCloudfrontUrl, UUID photoId, String photoCloudfrontUrl, String exhibitionTitle, String description) {
            this.writerId = writerId;
            this.nickname = nickname == null ? "" : nickname;
            this.profileCloudfrontUrl = profileCloudfrontUrl == null ? "" : profileCloudfrontUrl;
            this.photoId = photoId;
            this.photoCloudfrontUrl = photoCloudfrontUrl == null ? "" : photoCloudfrontUrl;
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
        private LocalDateTime reportedAt;
        private String description;
        private UUID writerId;
        private String nickname;
        private String profileCloudfrontUrl;

        private Long photoReportReceivedCount;
        private Long userReportReceivedCount;

        @Builder
        public GetDetailInfoResponse(
                String exhibitionTitle,
                UUID photoId,
                LocalDateTime reportedAt,
                String description,
                UUID writerId,
                String nickname,
                String profileCloudfrontUrl,
                Long photoReportReceivedCount,
                Long userReportReceivedCount
        ) {
            this.exhibitionTitle = exhibitionTitle == null ? "" : exhibitionTitle;
            this.photoId = photoId;
            this.reportedAt = reportedAt;
            this.description = description == null ? "" : description;
            this.writerId = writerId;
            this.nickname = nickname == null ? "" : nickname;
            this.profileCloudfrontUrl = profileCloudfrontUrl == null ? "" : profileCloudfrontUrl;

            this.photoReportReceivedCount = photoReportReceivedCount == null ? 0 : photoReportReceivedCount;
            this.userReportReceivedCount = userReportReceivedCount == null ? 0 : userReportReceivedCount;
        }
    }
}
