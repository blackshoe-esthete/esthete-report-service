package org.example.blackshoe.esthetereportservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.blackshoe.esthetereportservice.dto.CommentDto;
import org.example.blackshoe.esthetereportservice.dto.ResponseDto;
import org.example.blackshoe.esthetereportservice.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/comment")
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    @Operation(summary = "댓글 페이징 조회")
    @GetMapping
    public ResponseEntity<Page<CommentDto.ReadBasicInfo>> readComment(
            @RequestParam(defaultValue = "0") int page,//default
            @RequestParam(defaultValue = "10") int size
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
    public ResponseEntity<ResponseDto> deleteComment(
            @PathVariable String commentId
    ) {
        log.info("deleteComment");

        commentService.deleteComment(commentId);

        //no content
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @Operation(summary = "댓글 신고 요청 반려")
    @DeleteMapping("/{commentId}/reject")
    public ResponseEntity<ResponseDto> rejectDeleteComment(
            @PathVariable String commentId
    ) {
        log.info("rejectDeleteComment");

        commentService.rejectCommentReport(commentId);

        //no content
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
