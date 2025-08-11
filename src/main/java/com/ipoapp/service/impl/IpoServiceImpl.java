package com.ipoapp.service.impl;

import com.ipoapp.dto.IpoDto;
import com.ipoapp.exception.ResourceNotFoundException;
import com.ipoapp.model.Ipo;
import com.ipoapp.model.IpoStatus;
import com.ipoapp.repository.IpoRepository;
import com.ipoapp.service.IpoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the IPO service
 */
@Service
@RequiredArgsConstructor
public class IpoServiceImpl implements IpoService {

    private final IpoRepository ipoRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<IpoDto> getAllIpos() {
        return ipoRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public IpoDto getIpoById(Long id) {
        Ipo ipo = ipoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("IPO", "id", id));
        return mapToDto(ipo);
    }

    @Override
    @Transactional
    public IpoDto createIpo(IpoDto ipoDto) {
        Ipo ipo = mapToEntity(ipoDto);
        Ipo savedIpo = ipoRepository.save(ipo);
        return mapToDto(savedIpo);
    }

    @Override
    @Transactional
    public IpoDto updateIpo(Long id, IpoDto ipoDto) {
        Ipo ipo = ipoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("IPO", "id", id));
        
        // Update fields
        ipo.setCompanyName(ipoDto.getCompanyName());
        ipo.setIssuePrice(ipoDto.getIssuePrice());
        ipo.setTotalShares(ipoDto.getTotalShares());
        ipo.setOpenDate(ipoDto.getOpenDate());
        ipo.setCloseDate(ipoDto.getCloseDate());
        ipo.setStatus(ipoDto.getStatus());
        ipo.setDescription(ipoDto.getDescription());
        
        Ipo updatedIpo = ipoRepository.save(ipo);
        return mapToDto(updatedIpo);
    }

    @Override
    @Transactional
    public void deleteIpo(Long id) {
        Ipo ipo = ipoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("IPO", "id", id));
        ipoRepository.delete(ipo);
    }

    @Override
    public List<IpoDto> searchIposByCompanyName(String companyName) {
        return ipoRepository.findByCompanyNameContainingIgnoreCase(companyName).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<IpoDto> getIposByStatus(IpoStatus status) {
        return ipoRepository.findByStatus(status).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    /**
     * Maps IPO entity to DTO
     */
    private IpoDto mapToDto(Ipo ipo) {
        return modelMapper.map(ipo, IpoDto.class);
    }

    /**
     * Maps IPO DTO to entity
     */
    private Ipo mapToEntity(IpoDto ipoDto) {
        return modelMapper.map(ipoDto, Ipo.class);
    }
} 