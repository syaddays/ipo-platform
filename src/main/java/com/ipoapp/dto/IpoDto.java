package com.ipoapp.dto;

import com.ipoapp.model.IpoStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Data Transfer Object for IPO data
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IpoDto {

    private Long id;

    @NotBlank(message = "Company name is required")
    @Size(min = 2, max = 100, message = "Company name must be between 2 and 100 characters")
    private String companyName;

    @NotNull(message = "Issue price is required")
    @DecimalMin(value = "0.01", message = "Issue price must be greater than 0")
    private BigDecimal issuePrice;

    @NotNull(message = "Total shares is required")
    @Min(value = 1, message = "Total shares must be at least 1")
    private Long totalShares;

    @NotNull(message = "Open date is required")
    @FutureOrPresent(message = "Open date must be today or in the future")
    private LocalDate openDate;

    @NotNull(message = "Close date is required")
    @Future(message = "Close date must be in the future")
    private LocalDate closeDate;

    @NotNull(message = "Status is required")
    private IpoStatus status;

    private String description;
} 