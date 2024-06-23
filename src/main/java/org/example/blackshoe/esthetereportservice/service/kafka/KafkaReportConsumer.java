package org.example.blackshoe.esthetereportservice.service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.blackshoe.esthetereportservice.dto.KafkaConsumerDto;
import org.example.blackshoe.esthetereportservice.entity.Comment;
import org.example.blackshoe.esthetereportservice.entity.Photo;
import org.example.blackshoe.esthetereportservice.entity.Report;
import org.example.blackshoe.esthetereportservice.repository.CommentRepository;
import org.example.blackshoe.esthetereportservice.repository.PhotoRepository;
import org.example.blackshoe.esthetereportservice.repository.ReportRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service @Slf4j @RequiredArgsConstructor
public class KafkaReportConsumer {
    private final ObjectMapper objectMapper;
    private final ReportRepository reportRepository;
    private final CommentRepository commentRepository;
    private final PhotoRepository photoRepository;

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

        UUID reporterId = UUID.fromString(commentReport.getReporterId());
        UUID writerId = UUID.fromString(commentReport.getWriterId());

        Report report = Report.builder()
                .reporterId(reporterId)
                .writerId(writerId)
                .description(commentReport.getReportDescription())
                .type(commentReport.getReportType())
                .build();

        reportRepository.save(report);

        Comment comment = Comment.builder()
                .content(commentReport.getCommentContent())
                .build();

        UUID commentId = UUID.fromString(commentReport.getCommentId());

        comment.setCommentId(commentId);
        comment.updateReport(report);

        commentRepository.save(comment);

        acknowledgment.acknowledge();
    }

    @KafkaListener(topics = "photo-report")
    @Transactional
    public void reportPhoto(String payload, Acknowledgment acknowledgment) {
        log.info("received payload='{}'", payload);
        KafkaConsumerDto.PhotoReport photoReport = null;

        try {
            // 역직렬화
            photoReport = objectMapper.readValue(payload, KafkaConsumerDto.PhotoReport.class);
        } catch (Exception e) {
            log.error("Error while converting json string to photo object", e);

        }

        log.info("Photo info : {}", photoReport);

        UUID reporterId = UUID.fromString(photoReport.getReporterId());
        UUID writerId = UUID.fromString(photoReport.getWriterId());

        Report report = Report.builder()
                .reporterId(reporterId)
                .writerId(writerId)
                .description(photoReport.getReportDescription())
                .type(photoReport.getReportType())
                .build();

        reportRepository.save(report);

        UUID photoId = UUID.fromString(photoReport.getPhotoId());

        Photo photo = Photo.builder()
                .photoImgUrl(photoReport.getPhotoImgUrl())
                .exhibitionTitle(photoReport.getExhibitionTitle())
                .build();

        photo.setPhotoId(photoId);
        photo.updateReport(report);

        photoRepository.save(photo);

        acknowledgment.acknowledge();
    }
}
