package com.bitirme.api.GoogleBooks.model;

import org.springframework.format.annotation.DateTimeFormat;

public class SaleInfo {

    private String country;

    private String saleability;

    private Boolean isEbook;

    private ListPrice listPrice;

    private DateTimeFormat onSaleDate;

}
