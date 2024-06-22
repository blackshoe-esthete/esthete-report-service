package org.example.blackshoe.esthetereportservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.blackshoe.esthetereportservice.dto.PhotoDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/photo")
@RestController
@RequiredArgsConstructor
public class PhotoController {
    @Operation(summary = "사진 페이징 조회")
    @GetMapping("/{photoId}")
    public ResponseEntity<Page<PhotoDto.ReadBasicInfoResponse>> readPhotos(@PathVariable String photoId) {
        log.info("readPhotos");
        return null;
    }
    @Operation(summary = "사진 상세 조회")
    @GetMapping
    public void getComments() {
        log.info("getComments");
    }
    @Operation(summary = "사진 삭제")
    @DeleteMapping("/{photoId}")
    public void deleteComment() {
        log.info("deleteComment");
    }
    @Operation(summary = "사진 삭제 요청 반려")
    @DeleteMapping("/{photoId}/reject")
    public void rejectDeleteComment() {
        log.info("rejectDeleteComment");
    }
}
