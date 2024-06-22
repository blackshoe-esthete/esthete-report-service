package org.example.blackshoe.esthetereportservice.service;

import jakarta.persistence.PrePersist;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.blackshoe.esthetereportservice.dto.CommentDto;
import org.example.blackshoe.esthetereportservice.entity.Comment;
import org.example.blackshoe.esthetereportservice.repository.CommentRepository;
import org.example.blackshoe.esthetereportservice.repository.ReportRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j @RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final ReportRepository reportRepository;
    private final CommentRepository commentRepository;
    @Override
    public Page<CommentDto.ReadBasicInfo> readComments(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<CommentDto.ReadBasicInfo> photos = reportRepository.readComments(pageable);

        return photos;
    }

    @Override
    public CommentDto.GetDetailInfo getDetailComment(String commentId) {

        UUID commentUUID = UUID.fromString(commentId);

        return reportRepository.getCommentDetailByCommentId(commentUUID);
    }

    @PreAuthorize("isAuthenticated()")
    @Transactional
    @Override
    public void rejectCommentReport(String commentId) {
        UUID commentUUID = UUID.fromString(commentId);
        reportRepository.deleteByCommentId(commentUUID);
        commentRepository.deleteByCommentId(commentUUID);
    }
    @PreAuthorize("isAuthenticated()")
    @Transactional
    @Override
    public void deleteComment(String commentId) {
        UUID commentUUID = UUID.fromString(commentId);
        reportRepository.deleteByCommentId(commentUUID);
        commentRepository.deleteByCommentId(commentUUID);
        //@TODO : kafka event publish
    }
}
