package com.fairhold.service.impl;

import com.fairhold.dto.request.CreateResourceRequest;
import com.fairhold.dto.response.ResourceResponse;
import com.fairhold.entity.Resource;
import com.fairhold.repository.ResourceRepository;
import com.fairhold.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;

    @Override
    public ResourceResponse createResource(CreateResourceRequest request) {

        Resource resource = Resource.builder()
                .name(request.getName())
                .description(request.getDescription())
                .location(request.getLocation())
                .active(true)
                .build();

        Resource savedResource = resourceRepository.save(resource);

        return mapToResponse(savedResource);
    }

    @Override
    public List<ResourceResponse> getAllResources() {

        return resourceRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private ResourceResponse mapToResponse(Resource resource) {

        return ResourceResponse.builder()
                .id(resource.getId())
                .name(resource.getName())
                .description(resource.getDescription())
                .location(resource.getLocation())
                .active(resource.isActive())
                .build();
    }
}