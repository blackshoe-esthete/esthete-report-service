package org.example.blackshoe.esthetereportservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.blackshoe.esthetereportservice.dto.CommentDto;
import org.example.blackshoe.esthetereportservice.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/comment")
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    @Operation(summary = "댓글 리스트 조회")
    @GetMapping
    public ResponseEntity<Page<CommentDto.ReadBasicInfo>> readComment(
            int page,
            int size
    ) {
        log.info("read comments");
        Page<CommentDto.ReadBasicInfo> comments = commentService.readComments(page, size);
        return ResponseEntity.ok(comments);
    }
    @Operation(summary = "댓글 상세 조회")
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto.GetDetailInfo> getComments(
            @PathVariable String commentId
    ) {
        log.info("getComments");
        CommentDto.GetDetailInfo response = commentService.getDetailComment(commentId);
        return ResponseEntity.ok(response);
    }
    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/{commentId}")
    public void deleteComment() {
        log.info("deleteComment");
    }
    @Operation(summary = "댓글 삭제 요청 반려")
    @DeleteMapping("/{commentId}/reject")
    public void rejectDeleteComment() {
        log.info("rejectDeleteComment");
    }
}
