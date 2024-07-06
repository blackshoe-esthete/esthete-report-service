package org.example.blackshoe.esthetereportservice.service.kafka;

import org.example.blackshoe.esthetereportservice.dto.KafkaConsumerDto;
import org.example.blackshoe.esthetereportservice.entity.Report;
import org.example.blackshoe.esthetereportservice.exception.KafkaErrorResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.blackshoe.esthetereportservice.entity.User;
import org.example.blackshoe.esthetereportservice.exception.KafkaException;
import org.example.blackshoe.esthetereportservice.repository.CommentRepository;
import org.example.blackshoe.esthetereportservice.repository.PhotoRepository;
import org.example.blackshoe.esthetereportservice.repository.ReportRepository;
import org.example.blackshoe.esthetereportservice.repository.UserRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaUserInfoConsumer{
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    private final ReportRepository reportRepository;
    private final CommentRepository commentRepository;
    private final PhotoRepository photoRepository;
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

        UUID userId = UUID.fromString(userCreate.getUserId());

        User user = User.builder()
                .nickname(userCreate.getNickname())
                .build();

        user.setUserId(userId);

        userRepository.save(user);
        acknowledgment.acknowledge();
    }

    @KafkaListener(topics = "user-set-profile")
    @Transactional
    public void setProfileImgUrl(String payload, Acknowledgment acknowledgment) {
        log.info("received payload='{}'", payload);
        KafkaConsumerDto.UserProfileImgUrl userProfileImgUrlDto = null;

        try {
            // 역직렬화
            userProfileImgUrlDto = objectMapper.readValue(payload, KafkaConsumerDto.UserProfileImgUrl.class);
        } catch (Exception e) {
            log.error("Error while converting json string to user object", e);
        }

        log.info("User info : {}", userProfileImgUrlDto);

        UUID userId = UUID.fromString(userProfileImgUrlDto.getUserId());
        final User user = userRepository.findByUserId(userId).orElseThrow(() -> new KafkaException(KafkaErrorResult.USER_NOT_FOUND));

        user.updateProfileImgUrl(userProfileImgUrlDto.getProfileImgUrl());

        userRepository.save(user);
        acknowledgment.acknowledge();
    }

    @KafkaListener(topics = "user-set-nickname")
    @Transactional
    public void setNickname(String payload, Acknowledgment acknowledgment) {
        log.info("received payload='{}'", payload);
        KafkaConsumerDto.UserNickname userNicknameDto = null;

        try {
            // 역직렬화
            userNicknameDto = objectMapper.readValue(payload, KafkaConsumerDto.UserNickname.class);
        } catch (Exception e) {
            log.error("Error while converting json string to user object", e);
        }

        log.info("User info : {}", userNicknameDto);

        UUID userId = UUID.fromString(userNicknameDto.getUserId());
        final User user = userRepository.findByUserId(userId).orElseThrow(() -> new KafkaException(KafkaErrorResult.USER_NOT_FOUND));

        user.updateNickname(userNicknameDto.getNickname());

        userRepository.save(user);
        acknowledgment.acknowledge();
    }

    @KafkaListener(topics = "user-delete")
    @Transactional
    public void deleteUser(String payload, Acknowledgment acknowledgment) {
        log.info("received payload='{}'", payload);
        KafkaConsumerDto.UserDelete userNicknameDto = null;

        try {
            // 역직렬화
            userNicknameDto = objectMapper.readValue(payload, KafkaConsumerDto.UserDelete.class);
        } catch (Exception e) {
            log.error("Error while converting json string to user object", e);
        }

        log.info("User info : {}", userNicknameDto);

        UUID userId = UUID.fromString(userNicknameDto.getUserId());
        final User user = userRepository.findByUserId(userId).orElseThrow(() -> new KafkaException(KafkaErrorResult.USER_NOT_FOUND));

        userRepository.delete(user);

        final List<Report> reports = reportRepository.findByWriterId(userId);

        for (Report report : reports) {
            photoRepository.deleteByPhotoId(report.getPhoto().getPhotoId());
        }

        reportRepository.deleteByWriterId(userId);

        acknowledgment.acknowledge();
    }
//    @KafkaListener(topics = "user-delete")
//    @Transactional
//    public void deleteUser(String payload) {
//        log.info("received payload='{}'", payload);
//        KafkaConsumerDto.UserDelete userNicknameDto = null;
//
//        try {
//            // 역직렬화
//            userNicknameDto = objectMapper.readValue(payload, KafkaConsumerDto.UserDelete.class);
//        } catch (Exception e) {
//            log.error("Error while converting json string to user object", e);
//        }
//
//        log.info("User info : {}", userNicknameDto);
//
//        UUID userId = UUID.fromString(userNicknameDto.getUserId());
//        final User user = userRepository.findByUserId(userId).orElseThrow(() -> new KafkaException(KafkaErrorResult.USER_NOT_FOUND));
//
//        userRepository.delete(user);
//
//        final List<Report> reports = reportRepository.findByWriterId(userId);
//
//        for (Report report : reports) {
//            photoRepository.deleteByPhotoId(report.getPhoto().getPhotoId());
//        }
//
//        reportRepository.deleteByWriterId(userId);
//
//    }
}
