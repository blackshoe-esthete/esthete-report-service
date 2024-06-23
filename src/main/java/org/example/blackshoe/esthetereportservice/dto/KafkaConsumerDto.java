package org.example.blackshoe.esthetereportservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

public class KafkaConsumerDto {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CommentReport {
        private String reporterId;
        private String writerId;
        private String reportType;
        private String reportDescription;
        private String commentId;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class PhotoReport {
        private String reporterId;
        private String writerId;
        private String reportType;
        private String reportDescription;
        private String photoId;
    }

    @Getter
    @NoArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class UserCreate {
        private String userId;
        private String nickname;
        private String email;

        @Builder
        public UserCreate(String userId, String nickname, String email) {
            this.userId = userId;
            this.nickname = nickname;
            this.email = email;
        }
    }
    @Getter
    @NoArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class UserProfileImgUrl {
        private String userId;
        private String profileImgUrl;

        @Builder
        public UserProfileImgUrl(String userId, String profileImgUrl) {
            this.userId = userId;
            this.profileImgUrl = profileImgUrl;
        }
    }
}
