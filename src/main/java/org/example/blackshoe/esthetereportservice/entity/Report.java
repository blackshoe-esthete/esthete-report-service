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
@Table(name = "reports")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @Column(name = "reporter_uuid", columnDefinition = "BINARY(16)", unique = true, nullable = false)
    private UUID reporterId;

    @Column(name = "writer_uuid", columnDefinition = "BINARY(16)", unique = true, nullable = false)
    private UUID writerId;

    @Column(name = "definition", nullable = false, length = 50)
    private String definition;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @OneToOne(mappedBy = "report", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Comment comment;

    @OneToOne(mappedBy = "report", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Photo photo;

    @Builder
    public Report(UUID reporterId, UUID writerId, String definition, String type) {
        this.reporterId = reporterId;
        this.writerId = writerId;
        this.definition = definition;
        this.type = type;
    }
    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}

