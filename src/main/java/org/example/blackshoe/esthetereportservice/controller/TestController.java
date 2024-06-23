package org.example.blackshoe.esthetereportservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.blackshoe.esthetereportservice.dto.KafkaConsumerDto;
import org.example.blackshoe.esthetereportservice.service.PhotoService;
import org.example.blackshoe.esthetereportservice.service.kafka.KafkaUserInfoConsumer;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/test")
@RestController
@RequiredArgsConstructor
public class TestController {
    private final KafkaUserInfoConsumer kafkaUserInfoConsumer;
    private final ObjectMapper objectMapper;
//    @DeleteMapping("/kafka/user-delete")
//    public void testKafkaUserInfoConsumer() throws JsonProcessingException {
//        KafkaConsumerDto.UserDelete userDelete = KafkaConsumerDto.UserDelete.builder()
//                .userId("23e7b2b4-c1ac-4591-bb7f-c6706daf22aa")
//                .build();
//        kafkaUserInfoConsumer.deleteUser(objectMapper.writeValueAsString(userDelete));
//    }

}
