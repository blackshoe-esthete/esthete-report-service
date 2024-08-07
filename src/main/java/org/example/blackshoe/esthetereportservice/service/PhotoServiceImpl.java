package org.example.blackshoe.esthetereportservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.blackshoe.esthetereportservice.dto.KafkaProducerDto;
import org.example.blackshoe.esthetereportservice.dto.PhotoDto;
import org.example.blackshoe.esthetereportservice.repository.PhotoRepository;
import org.example.blackshoe.esthetereportservice.repository.ReportRepository;
import org.example.blackshoe.esthetereportservice.service.kafka.KafkaRemoveProducer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j @RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository photoRepository;
    private final ReportRepository reportRepository;
    private final KafkaRemoveProducer kafkaRemoveProducer;
    @Override
    public Page<PhotoDto.ReadBasicInfoResponse> readPhotos(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<PhotoDto.ReadBasicInfoResponse> photos = reportRepository.readPhotos(pageable);

        return photos;
    }

    @Override
    public PhotoDto.GetDetailInfoResponse getDetailPhoto(String photoId) {

        UUID photoUUID = UUID.fromString(photoId);

        return reportRepository.getPhotoDetailByPhotoId(photoUUID);
    }
    @PreAuthorize("isAuthenticated()")
    @Transactional
    @Override
    public void rejectPhotoReport(String photoId) {
        UUID photoUUID = UUID.fromString(photoId);

        reportRepository.deleteByPhotoId(photoUUID);
        photoRepository.deleteByPhotoId(photoUUID);
    }

    @PreAuthorize("isAuthenticated()")
    @Transactional
    @Override
    public void deletePhoto(String photoId) {
        UUID photoUUID = UUID.fromString(photoId);
        reportRepository.deleteByPhotoId(photoUUID);
        photoRepository.deleteByPhotoId(photoUUID);

        KafkaProducerDto.DeletePhoto deletePhoto = KafkaProducerDto.DeletePhoto.builder()
                .photoId(photoId)
                .build();

        kafkaRemoveProducer.deletePhoto(deletePhoto);

    }
}
