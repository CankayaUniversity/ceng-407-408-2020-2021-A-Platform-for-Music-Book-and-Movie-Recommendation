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
@Table(schema = "QUIREC", name = "BOOK")
@Data
@EqualsAndHashCode(callSuper = false, of = {})
@ToString(callSuper = true, of = {})
public class Book extends BaseModel {

    @Column(name = "RESOURCE_ID")
    public String resourceId;

    @Column(name = "TITLE")
    public String title;

    @Column(name = "AUTHOR")
    public String author;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            schema = "QUIREC", name = "BOOK_CATEGORIES",
            joinColumns = {@JoinColumn(name = "BOOK_ID", foreignKey = @ForeignKey(name = "FK_BOOK_CATEGORIES_BOOK_ID"))},
            inverseJoinColumns = {@JoinColumn(name = "CATEGORY_ID", foreignKey = @ForeignKey(name = "FK_BOOK_CATEGORIES_CATEGORY_ID"))}
    )
    private Set<Categories> categories = new HashSet<>();

}
