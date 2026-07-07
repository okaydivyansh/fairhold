package com.fairhold.service;

import com.fairhold.dto.request.CreateResourceRequest;
import com.fairhold.dto.response.ResourceResponse;

import java.util.List;

public interface ResourceService {

    ResourceResponse createResource(CreateResourceRequest request);

    List<ResourceResponse> getAllResources();
}