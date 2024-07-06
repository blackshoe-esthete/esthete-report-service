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
        private String profileImgUrl;
        private String description;
        private String commentContent;
        private UUID commentId;
        @Builder
        public ReadBasicInfo(UUID writerId, String nickname, String profileImgUrl, String description, String commentContent, UUID commentId) {
            this.writerId = writerId;
            this.nickname = nickname == null ? "" : nickname;
            this.profileImgUrl = profileImgUrl == null ? "" : profileImgUrl;
            this.description = description == null ? "" : description;
            this.commentContent = commentContent == null ? "" : commentContent;
            this.commentId = commentId;
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
        private String profileImgUrl;
        private String nickname;
        private UUID writerId;

        private Long userReportReceivedCount;

        private String commentContent;
        @Builder
        public GetDetailInfo(
                UUID commentId,
                LocalDateTime createdAt,
                LocalDateTime reportedAt,
                String description,
                String profileImgUrl,
                String nickname,
                UUID writerId,

                Long userReportReceivedCount,
                String commentContent
        ) {
            this.commentId = commentId;
            this.createdAt = createdAt;
            this.reportedAt = reportedAt;
            this.description = description;
            this.profileImgUrl = profileImgUrl;
            this.nickname = nickname;
            this.writerId = writerId;

            this.userReportReceivedCount = userReportReceivedCount;
            this.commentContent = commentContent;
        }
    }
}
