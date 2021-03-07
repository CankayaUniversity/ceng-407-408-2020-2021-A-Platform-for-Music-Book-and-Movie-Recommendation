package com.bitirme.quirec.user.model;

import com.bitirme.base.model.BaseModel;
import com.bitirme.quirec.questionnarie.model.Categories;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(schema = "QUIREC", name = "USER")
@Data
@EqualsAndHashCode(callSuper = false, of = {})
@ToString(callSuper = true, of = {})
public class User extends BaseModel {

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            schema = "QUIREC", name = "USER_CATEGORIES",
            joinColumns = {@JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_USER_CATEGORIES_USER_ID"))},
            inverseJoinColumns = {@JoinColumn(name = "CATEGORY_ID", foreignKey = @ForeignKey(name = "FK_USER_CATEGORIES_CATEGORY_ID"))}
    )
    private Set<Categories> categories;

}
