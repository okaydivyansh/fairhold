package com.fairhold.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResourceResponse {

    private Long id;

    private String name;

    private String description;

    private String location;

    private boolean active;
}