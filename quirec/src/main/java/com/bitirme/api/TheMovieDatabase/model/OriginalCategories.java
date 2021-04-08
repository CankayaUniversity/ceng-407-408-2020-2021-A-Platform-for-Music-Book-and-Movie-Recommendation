package com.bitirme.api.TheMovieDatabase.model;

import com.bitirme.base.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "QUIREC", name = "TMD_ORIGINAL_CATEGORIES")
@Data
@EqualsAndHashCode(callSuper = false, of = {})
@ToString(callSuper = true, of = {})
public class OriginalCategories extends BaseModel {

    @Column(name = "ORIGINAL_ID")
    private Integer originalId;

    @Column(name = "NAME")
    private String name;
    
}
