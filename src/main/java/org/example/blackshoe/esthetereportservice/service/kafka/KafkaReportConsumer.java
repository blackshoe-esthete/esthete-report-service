package org.example.blackshoe.esthetereportservice.service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.blackshoe.esthetereportservice.dto.KafkaConsumerDto;
import org.example.blackshoe.esthetereportservice.entity.Comment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service @Slf4j @RequiredArgsConstructor
public class KafkaReportConsumer {
    private final ObjectMapper objectMapper;
    //comment, 저장

    /*
        private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    @KafkaListener(topics = "user-create")
    @Transactional
    public void createUser(String payload, Acknowledgment acknowledgment) {
        log.info("received payload='{}'", payload);
        KafkaConsumerDto.UserCreate userCreate = null;

        try {
            // 역직렬화
            userCreate = objectMapper.readValue(payload, KafkaConsumerDto.UserCreate.class);
        } catch (Exception e) {
            log.error("Error while converting json string to user object", e);
        }

        log.info("User info : {}", userCreate);
        acknowledgment.acknowledge();


        UUID userId = UUID.fromString(userCreate.getUserId());

        User user = User.builder()
                .nickname(userCreate.getNickname())
                .build();

        user.setUserId(userId);

        userRepository.save(user);
    }
        comment
     */

    @KafkaListener(topics = "comment-report")
    @Transactional
    public void reportComment(String payload, Acknowledgment acknowledgment) {
        log.info("received payload='{}'", payload);
        KafkaConsumerDto.CommentReport commentReport = null;

        try {
            // 역직렬화
            commentReport = objectMapper.readValue(payload, KafkaConsumerDto.CommentReport.class);
        } catch (Exception e) {
            log.error("Error while converting json string to comment object", e);
        }

        log.info("Comment info : {}", commentReport);
        acknowledgment.acknowledge();
        /*
        private String reporterId;
        private String writerId;
        private String reportType;
        private String reportDescription;
        private String commentId;
         */

        UUID reporterId = UUID.fromString(commentReport.getReporterId());
        UUID writerId = UUID.fromString(commentReport.getWriterId());
        UUID commentId = UUID.fromString(commentReport.getCommentId());


    }

    //photo, 저장
}
