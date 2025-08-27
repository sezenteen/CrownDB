package com.exercise.crowndb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "measureunit")
public class Measureunit {
    @Id
    @Column(name = "MeasureUnitID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @Column(name = "Name", length = 50, columnDefinition = "NVARCHAR(100)")
    private String name;

    @Column(name = "packageSize")
    private Integer packageSize;

}