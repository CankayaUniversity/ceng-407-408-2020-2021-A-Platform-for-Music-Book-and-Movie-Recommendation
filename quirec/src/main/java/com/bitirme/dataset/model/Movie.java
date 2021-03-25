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
@Table(schema = "QUIREC", name = "MOVIE")
@Data
@EqualsAndHashCode(callSuper = false, of = {})
@ToString(callSuper = true, of = {})
public class Movie extends BaseModel {

    @Column(name = "RESOURCE_ID")
    public String resourceId;

    @Column(name = "TITLE")
    public String title;

    @Column(name = "POSTER")
    public String poster;

    @Column(name = "VIDEO")
    public boolean video;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            schema = "QUIREC", name = "MOVIE_CATEGORIES",
            joinColumns = {@JoinColumn(name = "MOVIE_ID", foreignKey = @ForeignKey(name = "FK_MOVIE_CATEGORIES_MOVIE_ID"))},
            inverseJoinColumns = {@JoinColumn(name = "CATEGORY_ID", foreignKey = @ForeignKey(name = "FK_MOVIE_CATEGORIES_CATEGORY_ID"))}
    )
    private Set<Categories> categories = new HashSet<>();

}
