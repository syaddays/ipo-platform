package com.ipoapp.controller;

import com.ipoapp.dto.IpoDto;
import com.ipoapp.model.IpoStatus;
import com.ipoapp.service.IpoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Controller for web UI endpoints
 */
@Controller
@RequiredArgsConstructor
public class WebController {

    private final IpoService ipoService;

    /**
     * Home page
     */
    @GetMapping("/")
    public String home(Model model) {
        List<IpoDto> ipos = ipoService.getAllIpos();
        model.addAttribute("ipos", ipos);
        model.addAttribute("statuses", IpoStatus.values());
        return "home";
    }

    /**
     * Filter IPOs by status
     */
    @GetMapping("/status/{status}")
    public String getIposByStatus(@PathVariable IpoStatus status, Model model) {
        List<IpoDto> ipos = ipoService.getIposByStatus(status);
        model.addAttribute("ipos", ipos);
        model.addAttribute("statuses", IpoStatus.values());
        model.addAttribute("selectedStatus", status);
        return "home";
    }

    /**
     * Search IPOs by company name
     */
    @GetMapping("/search")
    public String searchIpos(@RequestParam("query") String query, Model model) {
        List<IpoDto> ipos = ipoService.searchIposByCompanyName(query);
        model.addAttribute("ipos", ipos);
        model.addAttribute("statuses", IpoStatus.values());
        model.addAttribute("searchQuery", query);
        return "home";
    }
} 