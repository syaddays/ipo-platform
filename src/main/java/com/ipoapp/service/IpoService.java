package com.ipoapp.service;

import com.ipoapp.dto.IpoDto;
import com.ipoapp.model.IpoStatus;

import java.util.List;

/**
 * Service interface for IPO operations
 */
public interface IpoService {

    /**
     * Get all IPOs
     */
    List<IpoDto> getAllIpos();

    /**
     * Get IPO by ID
     */
    IpoDto getIpoById(Long id);

    /**
     * Create a new IPO
     */
    IpoDto createIpo(IpoDto ipoDto);

    /**
     * Update an existing IPO
     */
    IpoDto updateIpo(Long id, IpoDto ipoDto);

    /**
     * Delete an IPO
     */
    void deleteIpo(Long id);

    /**
     * Search IPOs by company name
     */
    List<IpoDto> searchIposByCompanyName(String companyName);

    /**
     * Get IPOs by status
     */
    List<IpoDto> getIposByStatus(IpoStatus status);
} 