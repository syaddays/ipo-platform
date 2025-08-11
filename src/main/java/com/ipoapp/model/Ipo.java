package com.ipoapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entity class representing an IPO
 */
@Entity
@Table(name = "ipos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "issue_price", nullable = false)
    private BigDecimal issuePrice;

    @Column(name = "total_shares", nullable = false)
    private Long totalShares;

    @Column(name = "open_date", nullable = false)
    private LocalDate openDate;

    @Column(name = "close_date", nullable = false)
    private LocalDate closeDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private IpoStatus status;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
} 