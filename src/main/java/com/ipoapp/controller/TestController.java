package com.ipoapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller for testing and health check endpoints
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    /**
     * Simple health check endpoint
     */
    @GetMapping("/health")
    public Map<String, Object> healthCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("message", "Application is running");
        response.put("timestamp", System.currentTimeMillis());
        return response;
    }
} 