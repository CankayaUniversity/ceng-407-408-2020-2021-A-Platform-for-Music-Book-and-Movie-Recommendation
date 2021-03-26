package com.bitirme.quirec.questionnarie.model;

import com.bitirme.base.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(schema = "QUIREC", name = "CATEGORIES")
@Data
@EqualsAndHashCode(callSuper = false, of = {})
@ToString(callSuper = true, of = {})
public class Categories extends BaseModel {

    @Column(name = "category_type")
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @Column(name = "name")
    private String name;

    @Column(name = "original_id")
    private long originalId;

}
