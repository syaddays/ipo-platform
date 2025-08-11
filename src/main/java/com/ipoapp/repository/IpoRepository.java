package com.ipoapp.repository;

import com.ipoapp.model.Ipo;
import com.ipoapp.model.IpoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for IPO entity
 */
@Repository
public interface IpoRepository extends JpaRepository<Ipo, Long> {

    /**
     * Find IPOs by company name containing the given string (case-insensitive)
     */
    List<Ipo> findByCompanyNameContainingIgnoreCase(String companyName);

    /**
     * Find IPOs by status
     */
    List<Ipo> findByStatus(IpoStatus status);
} 