package org.example.blackshoe.esthetereportservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.blackshoe.esthetereportservice.dto.PhotoDto;
import org.example.blackshoe.esthetereportservice.repository.ReportRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j @RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final ReportRepository reportRepository;

    @Override
    public Page<PhotoDto.ReadBasicInfoResponse> readPhotos(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<PhotoDto.ReadBasicInfoResponse> photos = reportRepository.readPhotos(pageable);

        return photos;
    }
}
