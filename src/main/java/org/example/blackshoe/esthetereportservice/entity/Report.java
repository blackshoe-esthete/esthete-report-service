package org.example.blackshoe.esthetereportservice.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.blackshoe.esthetereportservice.common.BaseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Table(name = "reports")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @Column(name = "reporter_uuid", columnDefinition = "BINARY(16)", nullable = false)
    private UUID reporterId;

    @Column(name = "writer_uuid", columnDefinition = "BINARY(16)", nullable = false)
    private UUID writerId;

    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @OneToOne(mappedBy = "report", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Comment comment;

    @OneToOne(mappedBy = "report", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Photo photo;

    @Column(name = "reported_at", nullable = false)
    private LocalDateTime reportedAt;

    @PrePersist
    public void prePersist() {
        if (this.reportedAt == null)
            this.reportedAt = LocalDateTime.now();
    }

    @Builder
    public Report(UUID reporterId, UUID writerId, String description, String type) {
        this.reporterId = reporterId;
        this.writerId = writerId;
        this.description = description;
        this.type = type;
    }
    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}

