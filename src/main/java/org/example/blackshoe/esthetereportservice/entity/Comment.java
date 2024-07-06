package org.example.blackshoe.esthetereportservice.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.blackshoe.esthetereportservice.common.BaseEntity;

import java.util.UUID;

@Entity
@Getter
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "comment_uuid", columnDefinition = "BINARY(16)", unique = true, nullable = false)
    private UUID commentId;

    @Column(name = "content", nullable = false, length = 50)
    private String content;

    public void setCommentId(UUID commentId) {
        this.commentId = commentId;
    }

    public void updateReport(Report report) {
        this.report = report;
        report.setComment(this);
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "report_id", foreignKey = @ForeignKey(name = "comments_fk_report_id"))
    private Report report;


    @Builder
    public Comment(String content) {
        this.content = content;
    }
}

