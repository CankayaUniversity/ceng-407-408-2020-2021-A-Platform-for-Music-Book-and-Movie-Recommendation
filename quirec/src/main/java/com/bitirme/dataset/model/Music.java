package com.bitirme.dataset.model;

import com.bitirme.base.model.BaseModel;
import com.bitirme.quirec.questionnarie.model.Categories;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "QUIREC", name = "MUSIC")
@Data
@EqualsAndHashCode(callSuper = false, of = {})
@ToString(callSuper = true, of = {})
public class Music extends BaseModel {

    @Column(name = "RESOURCE_ID")
    public Integer resourceId;

    @Column(name = "TITLE")
    public String title;

    @Column(name = "COVER")
    public String cover;

    @Column(name = "ARTIST")
    public String artist;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            schema = "QUIREC", name = "MUSIC_CATEGORIES",
            joinColumns = {@JoinColumn(name = "MUSIC_ID", foreignKey = @ForeignKey(name = "FK_MUSIC_CATEGORIES_MUSIC_ID"))},
            inverseJoinColumns = {@JoinColumn(name = "CATEGORY_ID", foreignKey = @ForeignKey(name = "FK_MUSIC_CATEGORIES_CATEGORY_ID"))}
    )
    private Set<Categories> categories = new HashSet<>();

}
