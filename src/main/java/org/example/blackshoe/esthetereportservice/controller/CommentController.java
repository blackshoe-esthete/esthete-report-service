package org.example.blackshoe.esthetereportservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/comment")
@RestController
@RequiredArgsConstructor
public class CommentController {

    @Operation(summary = "댓글 리스트 조회")
    @GetMapping("/{commentId}")
    public void getComment() {
        log.info("getComment");
    }
    @Operation(summary = "댓글 상세 조회")
    @GetMapping
    public void getComments() {
        log.info("getComments");
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
