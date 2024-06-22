package org.example.blackshoe.esthetereportservice.service;

import org.example.blackshoe.esthetereportservice.dto.PhotoDto;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface PhotoService {
    Page<PhotoDto.ReadBasicInfoResponse> readPhotos(int page, int size);

    PhotoDto.GetDetailInfoResponse getDetailPhoto(String photoId);

    void rejectPhotoReport(String photoId);
}
