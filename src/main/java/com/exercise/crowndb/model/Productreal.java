package com.exercise.crowndb.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productreal")
public class Productreal {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ProductID", nullable = false)
    private Product productID;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "CountProduct", nullable = false)
    private Double countProduct;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "Price", nullable = false)
    private Double price;

    @ColumnDefault("0.00")
    @Column(name = "RercentSale", precision = 5, scale = 2)
    private BigDecimal rercentSale;

    @ColumnDefault("0")
    @Column(name = "PriceSale")
    private Double priceSale;

    @Size(max = 250)
    @ColumnDefault("'Хоосон'")
    @Column(name = "Tailbar", length = 250, columnDefinition = "NVARCHAR(100)")
    private String tailbar;

    @ColumnDefault("0")
    @Column(name = "Zuruu")
    private Double zuruu;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ToollogoID", nullable = false)
    private Toollogo toollogoID;

}