package org.example.blackshoe.esthetereportservice.service;

import org.example.blackshoe.esthetereportservice.dto.PhotoDto;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface PhotoService {
    Page<PhotoDto.ReadBasicInfoResponse> readPhotos(int page, int size);

    PhotoDto.GetDetailInfoResponse getDetailPhoto(String photoId);

    void rejectPhotoReport(String photoId);

    @PreAuthorize("isAuthenticated()")
    @Transactional
    void deletePhotoReport(String photoId);
}
