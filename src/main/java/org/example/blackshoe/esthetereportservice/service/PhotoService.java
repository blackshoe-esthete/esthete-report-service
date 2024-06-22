package org.example.blackshoe.esthetereportservice.service;

import org.example.blackshoe.esthetereportservice.dto.PhotoDto;
import org.springframework.data.domain.Page;

public interface PhotoService {
    Page<PhotoDto.ReadBasicInfoResponse> readPhotos(int page, int size);

}
