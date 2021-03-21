package com.bitirme.quirec.api.GoogleBooks.model;

import lombok.Data;

@Data
public class VolumeResource {

    private String kind;

    private String id;

    private String etag;

    private String selfLink;

    private VolumeInfo volumeInfo;

}
