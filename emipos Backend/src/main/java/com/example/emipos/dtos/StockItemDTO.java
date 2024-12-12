package com.example.emipos.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class StockItemDTO {
    private Integer id;
    private String itemCode;
    private Integer batch;
    private String itemName;
    private String itemDescription;
    private Integer supplierId;
    private Integer mainCategoryId;
    private Integer categoryId;
    private Integer brandId;
    private Integer branchId;
    private Float quantity;
    private Float alertQuantity;
    private Float batchQuantity;
    private String itemSize;
    private Integer unitMeasurement;
    private Integer unitContainer;
    private BigDecimal supplierPrice;
    private String supplierDiscountType;
    private Float supplierPriceDiscount;
    private BigDecimal supplierDiscountedPrice;
    private String otherDiscountType;
    private Float costPriceDiscount;
    private BigDecimal costPrice;
    private Float wholesalePriceDiscount;
    private BigDecimal wholesalePrice;
    private Float minimumPriceDiscount;
    private BigDecimal minimumPrice;
    private Float sellingPriceDiscount;
    private BigDecimal sellingPrice;
    private String priceCode;
    private Boolean showPriceCode;
    private BigDecimal discountPrice;
    private Integer colorCode;
    private String barcode;
    private Boolean externalBarcode;
    private String reason;
    private Boolean bonusItem;
    private String bonusItemSellingType;
    private LocalDateTime purchasedDate;
    private Boolean active=true;
}
