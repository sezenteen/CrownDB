package com.exercise.crowndb.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "toollogo")
public class Toollogo {
    @Id
    @Column(name = "ToollogoID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 250)
    @NotNull
    @Column(name = "Name", nullable = false, columnDefinition = "NVARCHAR(100)")
    private String name;

    @NotNull
    @Column(name = "Ognoo", nullable = false)
    private Instant ognoo;

    @Size(max = 10)
    @Column(name = "Tailbar", length = 10)
    private String tailbar;

}