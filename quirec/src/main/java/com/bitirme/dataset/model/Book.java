package com.bitirme.dataset.model;

import com.bitirme.base.model.BaseModel;
import com.bitirme.quirec.questionnarie.model.Categories;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "QUIREC", name = "BOOK")
@Data
@EqualsAndHashCode(callSuper = false, of = {})
@ToString(callSuper = true, of = {})
public class Book extends BaseModel {

    @Column(name = "RESOURCE_ID") //TODO: Search&details için kullanılacak
    public String resourceId;

    @Column(name = "TITLE")
    public String title;

    @Column(name = "AUTHOR")
    public String author;

}
