package com.fairhold.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/api/test")
    public String test()
    {
        return "FairHold is running";
    }
}
