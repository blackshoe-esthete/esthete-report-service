package org.example.blackshoe.esthetereportservice.service.kafka;

import org.example.blackshoe.esthetereportservice.exception.KafkaErrorResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.blackshoe.esthetereportservice.dto.KafkaProducerDto;
import org.example.blackshoe.esthetereportservice.exception.KafkaException;
import org.springframework.stereotype.Service;

@Service @Slf4j @RequiredArgsConstructor
public class KafkaRemoveProducer {
    private final KafkaProducer kafkaProducer;
    private final ObjectMapper objectMapper;

    public void deletePhoto(KafkaProducerDto.DeletePhoto deletePhotoMsg) {
        String topic = "photo-delete";

        String photoJsonString;
        try {
            photoJsonString = objectMapper.writeValueAsString(deletePhotoMsg);
        } catch (JsonProcessingException e) {
            log.error("Error while converting photo object to json string", e);
            //throw new KafkaException(() -> KafkaErrorResult.JSON_CONVERSION_ERROR);
            throw new KafkaException(KafkaErrorResult.JSON_CONVERSION_ERROR);
        }

        kafkaProducer.send(topic, photoJsonString);
    }

    public void deleteComment(KafkaProducerDto.DeleteComment deleteCommentMsg) {
        String topic = "comment-delete";

        String photoJsonString;
        try {
            photoJsonString = objectMapper.writeValueAsString(deleteCommentMsg);
        } catch (JsonProcessingException e) {
            log.error("Error while converting photo object to json string", e);
            throw new KafkaException(KafkaErrorResult.JSON_CONVERSION_ERROR);
        }

        kafkaProducer.send(topic, photoJsonString);
    }
}
