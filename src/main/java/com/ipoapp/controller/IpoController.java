package com.ipoapp.controller;

import com.ipoapp.dto.ApiResponse;
import com.ipoapp.dto.IpoDto;
import com.ipoapp.model.IpoStatus;
import com.ipoapp.service.IpoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * REST controller for IPO operations
 */
@RestController
@RequestMapping("/api/ipos")
@RequiredArgsConstructor
public class IpoController {

    private final IpoService ipoService;

    /**
     * Get all IPOs
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<IpoDto>>> getAllIpos() {
        List<IpoDto> ipos = ipoService.getAllIpos();
        return ResponseEntity.ok(ApiResponse.success(ipos));
    }

    /**
     * Get IPO by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<IpoDto>> getIpoById(@PathVariable Long id) {
        IpoDto ipo = ipoService.getIpoById(id);
        return ResponseEntity.ok(ApiResponse.success(ipo));
    }

    /**
     * Create a new IPO
     */
    @PostMapping
    public ResponseEntity<ApiResponse<IpoDto>> createIpo(@Valid @RequestBody IpoDto ipoDto) {
        IpoDto createdIpo = ipoService.createIpo(ipoDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("IPO created successfully", createdIpo));
    }

    /**
     * Update an existing IPO
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<IpoDto>> updateIpo(
            @PathVariable Long id,
            @Valid @RequestBody IpoDto ipoDto) {
        IpoDto updatedIpo = ipoService.updateIpo(id, ipoDto);
        return ResponseEntity.ok(ApiResponse.success("IPO updated successfully", updatedIpo));
    }

    /**
     * Delete an IPO
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteIpo(@PathVariable Long id) {
        ipoService.deleteIpo(id);
        return ResponseEntity.ok(ApiResponse.success("IPO deleted successfully", null));
    }

    /**
     * Search IPOs by company name
     */
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<IpoDto>>> searchIposByCompanyName(
            @RequestParam("name") String companyName) {
        List<IpoDto> ipos = ipoService.searchIposByCompanyName(companyName);
        return ResponseEntity.ok(ApiResponse.success(ipos));
    }

    /**
     * Get IPOs by status
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<IpoDto>>> getIposByStatus(
            @PathVariable IpoStatus status) {
        List<IpoDto> ipos = ipoService.getIposByStatus(status);
        return ResponseEntity.ok(ApiResponse.success(ipos));
    }
} 