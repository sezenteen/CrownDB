package com.exercise.crowndb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "ProductID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 250)
    @NotNull
    @Column(name = "Name", nullable = false, length = 250, columnDefinition = "NVARCHAR(100)")
    private String name;

    @Size(max = 250)
    @Column(name = "ShortName", length = 250, columnDefinition = "NVARCHAR(100)")
    private String shortName;

    @Size(max = 50)
    @ColumnDefault("'0'")
    @Column(name = "Barcode", length = 50, unique = true)
    private String barcode;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "Price", nullable = false)
    private Double price;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CategoryID", nullable = false)
    private Category categoryID;

    @ColumnDefault("0")
    @Column(name = "allowCityTax")
    private Boolean allowCityTax;

    @Size(max = 50)
    @Column(name = "CustomCode", length = 50, columnDefinition = "NVARCHAR(100)")
    private String customCode;

    @Size(max = 250)
    @ColumnDefault("'0'")
    @Column(name = "ImagePath", length = 250)
    private String imagePath;

    @ColumnDefault("1")
    @Column(name = "packageCount")
    private Integer packageCount;

    @Size(max = 50)
    @ColumnDefault("'0'")
    @Column(name = "MainCategoryCode", length = 50, columnDefinition = "NVARCHAR(100)")
    private String mainCategoryCode;

    @ColumnDefault("0")
    @Column(name = "isVATFree")
    private Boolean isVATFree;

    public Product() {
    }

    public Product(Long id, String name, String shortName, String barcode, Double price, Category categoryID, Boolean allowCityTax, String customCode, String imagePath, Integer packageCount, String mainCategoryCode, Boolean isVATFree) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.barcode = barcode;
        this.price = price;
        this.categoryID = categoryID;
        this.allowCityTax = allowCityTax;
        this.customCode = customCode;
        this.imagePath = imagePath;
        this.packageCount = packageCount;
        this.mainCategoryCode = mainCategoryCode;
        this.isVATFree = isVATFree;
    }
}