package com.example.emipos.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "stockitem")
public class StockItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "itemcode", nullable = false, length = 45)
    private String itemCode;

    @Column(name = "batch")
    private Integer batch;

    @Column(name = "itemname", nullable = false, length = 200)
    private String itemName;

    @Column(name = "itemdescription", length = 200)
    private String itemDescription;

    @Column(name = "supplierId", nullable = false)
    private Integer supplierId = -1;

    @Column(name = "mainCategoryId")
    private Integer mainCategoryId = -1;

    @Column(name = "categoryId")
    private Integer categoryId = -1;

    @Column(name = "brandId")
    private Integer brandId = -1;

    @Column(name = "branchId")
    private Integer branchId = -1;

    @Column(name = "quantity")
    private Float quantity;

    @Column(name = "alertQuantity")
    private Float alertQuantity = 0f;

    @Column(name = "batchQuantity")
    private Float batchQuantity = 0f;

    @Column(name = "itemsize", length = 45)
    private String itemSize;

    @Column(name = "unitMeasurement")
    private Integer unitMeasurement = -1;

    @Column(name = "unitContainer")
    private Integer unitContainer = -1;

    @Column(name = "supplierPrice", precision = 10, scale = 4)
    private BigDecimal supplierPrice = BigDecimal.ZERO;

    @Column(name = "supplierdiscounttype", length = 45)
    private String supplierDiscountType;

    @Column(name = "supplierpricediscount")
    private Float supplierPriceDiscount = 0f;

    @Column(name = "supplierdiscountedprice", precision = 10, scale = 4)
    private BigDecimal supplierDiscountedPrice = BigDecimal.ZERO;

    @Column(name = "otherDiscountType", length = 45)
    private String otherDiscountType;

    @Column(name = "costpricediscount", nullable = false)
    private Float costPriceDiscount = 0f;

    @Column(name = "costprice" ,nullable = false, precision = 10, scale = 4)
    private BigDecimal costPrice;

    @Column(name = "wholesalepricediscount")
    private Float wholesalePriceDiscount = 0f;

    @Column(name = "wholesaleprice", precision = 10, scale = 4)
    private BigDecimal wholesalePrice;

    @Column(name = "minimumpricediscount")
    private Float minimumPriceDiscount = 0f;

    @Column(name = "minimumprice", precision = 10, scale = 4, nullable = false)
    private BigDecimal minimumPrice;

    @Column(name = "sellingpricediscount")
    private Float sellingPriceDiscount = 0f;

    @Column(name = "sellingprice", precision = 10, scale = 4, nullable = false)
    private BigDecimal sellingPrice;

    @Column(name = "priceCode", length = 45)
    private String priceCode;

    @Column(name = "showPriceCode", nullable = false)
    private Boolean showPriceCode = false;

    @Column(name = "discountPrice", nullable = false, precision = 10, scale = 4)
    private BigDecimal discountPrice = BigDecimal.ZERO;

    @Column(name = "colorcode")
    private Integer colorCode;

    @Column(name = "barcode", length = 45)
    private String barcode;

    @Column(name = "externalbarcode", nullable = false)
    private Boolean externalBarcode = false;

    @Column(name = "reason", length = 45)
    private String reason;

    @Column(name = "bonusitem")
    private Boolean bonusItem = false;

    @Column(name = "bonusItemSellingType", length = 45)
    private String bonusItemSellingType;

    @Column(name = "purchasedDate")
    private LocalDateTime purchasedDate;

    @CreatedBy
    @Column(name = "creator", length = 45, updatable = false)
    private String creator;

    @CreatedDate
    @Column(name = "createdDate", updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedBy
    @Column(name = "updatedBy", length = 45)
    private String updatedBy;

    @LastModifiedDate
    @Column(name = "updatedDate")
    private LocalDateTime updatedDate;

    @Column(name = "active")
    private Boolean active=true;


}
