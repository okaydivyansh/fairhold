package com.fairhold.entity;

import jakarta.persistence.*;
import lombok.*;

//Conference Room A
//Tennis Court 1
//Library Study Room
//Lab Slot Machine
@Entity
@Table(name = "resources")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private boolean active;
}