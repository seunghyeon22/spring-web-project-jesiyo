package com.metacoding.web_project.report;


import com.metacoding.web_project.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Table(name = "report_tb")
@Getter
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reportId;

    @ManyToOne(fetch = FetchType.LAZY)
    private User reporterId;

    @ManyToOne(fetch = FetchType.LAZY)
    private User reportedId;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private Integer reportStatus;

    @Builder
    public Report(Integer reportId, User reporterId, User reportedId, String reason, Integer reportStatus) {
        this.reportId = reportId;
        this.reporterId = reporterId;
        this.reportedId = reportedId;
        this.reason = reason;
        this.reportStatus = reportStatus;
    }
}