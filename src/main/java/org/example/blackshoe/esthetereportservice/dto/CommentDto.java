package org.example.blackshoe.esthetereportservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

public class CommentDto {
    @Data
    @NoArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ReadBasicInfo {
        private UUID writerId;
        private String nickname;
        private String profileCloudfrontUrl;
        private String description;
        @Builder
        public ReadBasicInfo(UUID writerId, String nickname, String profileCloudfrontUrl, String description) {
            this.writerId = writerId;
            this.nickname = nickname == null ? "" : nickname;
            this.profileCloudfrontUrl = profileCloudfrontUrl == null ? "" : profileCloudfrontUrl;
            this.description = description == null ? "" : description;
        }
    }

    @Data
    @NoArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class GetDetailInfo {
        private UUID commentId;
        private LocalDateTime createdAt;
        private LocalDateTime reportedAt;
        private String description;
        private String profileCloudfrontUrl;
        private String nickname;
        private UUID writerId;

        private Long userReportReceivedCount;

        @Builder
        public GetDetailInfo(
                UUID commentId,
                LocalDateTime createdAt,
                LocalDateTime reportedAt,
                String description,
                String profileCloudfrontUrl,
                String nickname,
                UUID writerId,

                Long userReportReceivedCount
        ) {
            this.commentId = commentId;
            this.createdAt = createdAt;
            this.reportedAt = reportedAt;
            this.description = description;
            this.profileCloudfrontUrl = profileCloudfrontUrl;
            this.nickname = nickname;
            this.writerId = writerId;

            this.userReportReceivedCount = userReportReceivedCount;
        }
    }
}
