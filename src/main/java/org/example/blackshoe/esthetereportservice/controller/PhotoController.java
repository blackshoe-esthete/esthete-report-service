package org.example.blackshoe.esthetereportservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import kong.unirest.HttpStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.example.blackshoe.esthetereportservice.dto.PhotoDto;
import org.example.blackshoe.esthetereportservice.dto.ResponseDto;
import org.example.blackshoe.esthetereportservice.service.PhotoService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/photo")
@RestController
@RequiredArgsConstructor
public class PhotoController {
    private final PhotoService photoService;
    @Operation(summary = "사진 페이징 조회")
    @GetMapping
    public ResponseEntity<Page<PhotoDto.ReadBasicInfoResponse>> readPhotos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        log.info("readReportedPhotos");

        Page<PhotoDto.ReadBasicInfoResponse> photos = photoService.readPhotos(page, size);

        return ResponseEntity.ok(photos);
    }
    @Operation(summary = "사진 상세 조회")
    @GetMapping("/{photoId}")
    public ResponseEntity<PhotoDto.GetDetailInfoResponse> getComments(
            @PathVariable String photoId
    ) {
        log.info("get detail photo");

        PhotoDto.GetDetailInfoResponse response = photoService.getDetailPhoto(photoId);

        return ResponseEntity.ok(response);
    }
    @Operation(summary = "사진 삭제")
    @DeleteMapping("/{photoId}")
    public ResponseEntity<ResponseDto> deletePhoto(
            @PathVariable String photoId
    ) {
        log.info("deletePhoto");

        photoService.deletePhoto(photoId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @Operation(summary = "사진 삭제 요청 반려")
    @DeleteMapping("/{photoId}/reject")
    public ResponseEntity<ResponseDto> rejectDeletePhoto(
            @PathVariable String photoId
    ) {

        log.info("rejectDeleteComment");

        photoService.rejectPhotoReport(photoId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
