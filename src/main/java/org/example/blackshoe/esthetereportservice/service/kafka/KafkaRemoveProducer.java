package org.example.blackshoe.esthetereportservice.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.blackshoe.esthetereportservice.dto.KafkaDto;
import org.springframework.stereotype.Service;

@Service @Slf4j @RequiredArgsConstructor
public class KafkaRemoveProducer {
    private final KafkaProducer kafkaProducer;
    private final ObjectMapper objectMapper;

    /*
        @Override
    public void createUser(KafkaDto.UserInfo userInfo) {
        String topic = "user-create";

        String userJsonString;
        try{
            //직렬화
            userJsonString = objectMapper.writeValueAsString(userInfo);
        } catch (JsonProcessingException e) {
            log.error("Error while converting user object to json string", e);
            throw new RuntimeException("Error while converting user object to json string", e);
        }

        kafkaProducer.send(topic, userJsonString);
    }
     */

    public void deletePhoto(KafkaDto.DeletePhoto deletePhotoMsg) {
        String topic = "photo-delete";

        String photoJsonString;
        try {
            photoJsonString = objectMapper.writeValueAsString(deletePhotoMsg);
        } catch (JsonProcessingException e) {
            log.error("Error while converting photo object to json string", e);
            throw new RuntimeException("Error while converting photo object to json string", e);
        }

        kafkaProducer.send(topic, photoJsonString);
    }

    public void deleteComment(KafkaDto.DeleteComment deleteCommentMsg) {
        String topic = "comment-delete";

        String photoJsonString;
        try {
            photoJsonString = objectMapper.writeValueAsString(deleteCommentMsg);
        } catch (JsonProcessingException e) {
            log.error("Error while converting photo object to json string", e);
            throw new RuntimeException("Error while converting photo object to json string", e);
        }

        kafkaProducer.send(topic, photoJsonString);
    }
}
