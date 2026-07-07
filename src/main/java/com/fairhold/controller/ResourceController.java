package com.fairhold.controller;

import com.fairhold.dto.request.CreateResourceRequest;
import com.fairhold.dto.response.ResourceResponse;
import com.fairhold.service.ResourceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @PostMapping
    public ResponseEntity<ResourceResponse> createResource(
            @Valid @RequestBody CreateResourceRequest request) {

        ResourceResponse response = resourceService.createResource(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<ResourceResponse>> getAllResources() {

        List<ResourceResponse> response = resourceService.getAllResources();

        return ResponseEntity.ok(response);
    }
}