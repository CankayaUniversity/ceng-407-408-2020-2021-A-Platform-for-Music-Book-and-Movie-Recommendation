package com.bitirme.quirec.api.GoogleBooks.model;

import lombok.Data;

import java.util.List;

@Data
public class VolumeInfo {

    private String title;

    private String subtitle;

    private List<String> authors;

    private String publisher;

    private String publishedDate;

    private String description;

    private List<IndustryIdentifiers> industryIdentifiers;

    private ReadingModes readingModes;

    private Integer pageCount;

    private Dimension dimension;

    private String printType;

    private List<String> categories;

    private Double averageRating;

    private Integer ratingsCount;

    private String maturityRating;

    private Boolean allowAnonLogging;

    private String contentVersion;

    private PanelizationSummary panelizationSummary;

    private ImageLinks imageLinks;

    private String language;

    private String previewLink;

    private String infoLink;

    private String canonicalVolumeLink;

    private SaleInfo saleInfo;

    private AccessInfo accessInfo;

    private SearchInfo searchInfo;

    private String mainCategory; //önemli

}
