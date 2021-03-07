package com.bitirme.quirec.recommendation.model;

import com.bitirme.base.model.BaseModel;
import com.bitirme.quirec.questionnarie.model.CategoryType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(schema = "QUIREC", name = "RATING")
@Data
@EqualsAndHashCode(callSuper = false, of = {})
@ToString(callSuper = true, of = {})
public class Rating extends BaseModel {

    @Column(name = "category_type")
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @Column(name = "category_rate")
    private int rate;

    @Column(name = "vote_number")
    private int voteNumber;

}