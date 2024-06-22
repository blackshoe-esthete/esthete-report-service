package org.example.blackshoe.esthetereportservice.service;

import org.example.blackshoe.esthetereportservice.dto.CommentDto;
import org.springframework.data.domain.Page;

public interface CommentService {
    Page<CommentDto.ReadBasicInfo> readComments(int page, int size);

    CommentDto.GetDetailInfo getDetailComment(String commentId);
}
